package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.Bill;
import com.neuedu.lab.model.po.Doctor;
import com.neuedu.lab.model.po.Register;
import com.neuedu.lab.socket.WebSocket;
import com.neuedu.lab.utils.ConstantDefinition;
import com.neuedu.lab.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

import static com.neuedu.lab.utils.ConstantUtils.responseFail;
import static com.neuedu.lab.utils.ConstantUtils.responseSuccess;

/**
 * @author wp 20164917
 */
@Service
public class RegisterService {
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private RegisterMapper registerMapper;
    @Resource
    private BillMapper billMapper;
    @Resource
    private RegisterLevelMapper registerLevelMapper;
    @Resource
    private PatientMapper patientMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

/*    public List<Department> getAllDepartments(){
        return departmentMapper.getAllDepartments();
    }*/

//    public JSONArray getAllDoctorsByDepartment(String id){
//        List<RegisterLevel> levelList = registerLevelMapper.getAllRegisterLevels();
//        JSONArray result = new JSONArray();
//        for(RegisterLevel registerLevel:levelList){
//            JSONObject doctorRegisterLevel  = new JSONObject();
//            doctorRegisterLevel.put("register_level",registerLevel);
//            doctorRegisterLevel.put("doctor",doctorMapper.getAllDoctorsByDepartment(id,registerLevel.getRegister_level_id()));
//            result.add(doctorRegisterLevel);
//        }
//        return result;
//    }
    public JSONObject getAllDoctorsByDepartment(String id,Integer register_level_id){
        try{
            return responseSuccess(doctorMapper.getAllDoctorsByDepartment(id,register_level_id));
        }catch (Exception e){
            return responseFail(null);
        }
    }

    public JSONObject addRegisterWithRedis(Register register) {
        String currentRegister = "addRegister"+register.getRegister_info_doctor_id();
        while (redisTemplate.opsForHash().hasKey("functions", currentRegister)) {
            try {
                Thread.sleep(100);
                System.out.println("Someone is registering!!!!!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }redisTemplate.opsForHash().put("functions",currentRegister,"whatever");
        JSONObject obj =  addRegister(register);
        redisTemplate.opsForHash().delete("functions",currentRegister);
        return obj;
    }

    @Transactional
    public synchronized JSONObject addRegister(Register register){

        //首先查看医生是否有号
        Doctor doctor;
        try {
            doctor =doctorMapper.getDoctorById(register.getRegister_info_doctor_id());
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail("获取医生信息出错");
        }
        if (doctor==null){
            return responseFail("当前医生不存在");
        }else if(!doctor.isDoctor_arrange_or_not()){
            return responseFail("医生"+doctor.getDoctor_name()+"今天不排班");
        }else if(doctor.getDoctor_register_num()==null || doctor.getDoctor_register_num()==0){
            return responseFail("医生"+doctor.getDoctor_name()+"今天不排班或排班已满");
        }

        //开始挂号操作
        try {
            //修正挂号状态
            register.setRegister_info_state(ConstantDefinition.REGISTER_STATE[0]);
            //检测病人是否已挂同一个医生的号
            if(registerMapper.checkVaild(register).size()>0){
                return responseFail("该挂号已提交，请勿重复提交");
            }
            //将医生的号源数量减一
            doctorMapper.updateDoctorRegisterNum(doctor.getDoctor_id(),doctor.getDoctor_register_num()-1);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail("验证过程出错",null);
        }

        //添加挂号记录
        try{
            registerMapper.addRegister(register);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail("添加挂号记录过程出错",null);
        }
        //为挂号记录填充病人
        try{
            register.setPatient(patientMapper.getPatientByRecordId(register.getRegister_info_patient_id()));
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail("填充病人信息过程出错",null);
        }
        register.setDoctor(doctorMapper.getDoctorByRegisterId(register.getRegister_info_id()));
        WebSocket.sendInfo("请刷新科室",doctor.getDoctor_department_id());
        return responseSuccess(register);
    }


    public JSONObject addBill(Bill bill){
        try {
            bill.setBill_type(ConstantDefinition.BILL_TYPE[0]);
            bill.setBill_time(new java.sql.Date(new Date().getTime()));
            billMapper.addBill(bill);
        }catch (Exception e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(billMapper.getBillById(bill.getBill_id()));
    }

    public JSONObject updateBill( String bill_num, Integer bill_id){
        try{
            billMapper.updateBillNum(bill_num,bill_id);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(billMapper.getBillById(bill_id));
    }


    @Transactional
    public JSONObject refund(Integer register_id) {
        Register register = registerMapper.getRegister(register_id);

        Bill bill;
        if(!register.getRegister_info_state().equals(ConstantDefinition.REGISTER_STATE[0])){
            return responseFail("不满足退号条件");
        }
        else {
            try{
                registerMapper.updateRegisterState(register_id,ConstantDefinition.REGISTER_STATE[3]);
                bill = billMapper.getRegisterBillForRefund(register_id,ConstantDefinition.BILL_STATE[0],
                        ConstantDefinition.BILL_STATE[2],ConstantDefinition.BILL_STATE[3]);
                bill.setBill_sum(ConstantUtils.convertToNegtive(bill.getBill_sum()));
                bill.setBill_state(ConstantDefinition.BILL_STATE[4]);
                billMapper.addBill(bill);

                register.setDoctor(doctorMapper.getDoctorByRegisterId(register_id));
            }catch (Exception e){
                e.printStackTrace();
                return responseFail();
            }
            WebSocket.sendInfo("请刷新科室",register.getDoctor().getDoctor_department_id());
            return responseSuccess(billMapper.getBillById(bill.getBill_id()));
        }
    }
}
