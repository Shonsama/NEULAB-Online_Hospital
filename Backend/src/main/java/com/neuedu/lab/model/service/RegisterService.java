package com.neuedu.lab.model.service;

import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.BillMapper;
import com.neuedu.lab.model.mapper.DepartmentMapper;
import com.neuedu.lab.model.mapper.DoctorMapper;
import com.neuedu.lab.model.mapper.RegisterMapper;
import com.neuedu.lab.model.po.Bill;
import com.neuedu.lab.model.po.Doctor;
import com.neuedu.lab.model.po.Register;
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

/*    public List<Department> getAllDepartments(){
        return departmentMapper.getAllDepartments();
    }*/

    public List<Doctor> getAllDoctorsByDepartment(String id){
        return doctorMapper.getAllDoctorsByDepartment(id);
    }


    public boolean addRegister(Register register){
        try {
            register.setRegister_info_state(ConstantDefinition.REGISTER_STATE[0]);
            registerMapper.addRegister(register);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
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
