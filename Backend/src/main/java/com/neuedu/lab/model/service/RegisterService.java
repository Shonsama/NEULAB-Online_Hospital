package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.Bill;
import com.neuedu.lab.model.po.Doctor;
import com.neuedu.lab.model.po.Register;
import com.neuedu.lab.model.po.RegisterLevel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

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
            return ConstantUtils.responseSuccess(doctorMapper.getAllDoctorsByDepartment(id,register_level_id));
        }catch (Exception e){
            return ConstantUtils.responseFail(null);
        }
    }

    public JSONObject addRegister(Register register){
        try {
            register.setRegister_info_state(ConstantDefinition.REGISTER_STATE[0]);
            registerMapper.addRegister(register);
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail(ConstantDefinition.FAIL_INSERT_MESSAGE,null);
        }
        return ConstantUtils.responseSuccess(register);
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
                bill.setBill_actual_sum(ConstantUtils.convertToNegtive(bill.getBill_actual_sum()));
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
