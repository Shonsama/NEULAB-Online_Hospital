package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.Bill;
import com.neuedu.lab.model.po.Doctor;
import com.neuedu.lab.model.po.Register;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.neuedu.lab.Utils.ConstantUtils.responseFail;
import static com.neuedu.lab.Utils.ConstantUtils.responseSuccess;

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

    @Transactional
    public JSONObject addRegister(Register register){

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
        return responseSuccess(register);
    }


    public boolean addBill(Bill bill){
        try {
            bill.setBill_type(ConstantDefinition.BILL_TYPE[0]);
            billMapper.addBill(bill);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Transactional
    public boolean refund(Integer register_id) {
        Register register = registerMapper.getRegister(register_id);
        if(!register.getRegister_info_state().equals(ConstantDefinition.REGISTER_STATE[0])){
            System.out.println(register.getRegister_info_state());
            System.out.println("不满足退号条件");
            return false;
        }
        else {
            try{
                registerMapper.updateRegisterState(register_id,ConstantDefinition.REGISTER_STATE[3]);
                Bill bill = billMapper.getBill(register_id);
                bill.setBill_sum(ConstantUtils.convertToNegtive(bill.getBill_sum()));
                bill.setBill_sum(ConstantUtils.convertToNegtive(bill.getBill_sum()));
                billMapper.addBill(bill);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
}
