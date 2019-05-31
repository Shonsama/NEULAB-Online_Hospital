package com.neuedu.lab.controller;

import com.neuedu.lab.model.po.Bill;
import com.neuedu.lab.model.po.Doctor;
import com.neuedu.lab.model.po.Register;
import com.neuedu.lab.model.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

/*    @RequestMapping("/get-all-department")
    public List<Department> getAllDepartment(){
        return registerService.getAllDepartments();
    }*/

    @RequestMapping("/get-all-doctor")
    public List<Doctor> getAllDoctorsByDepartment(@RequestBody String department_id){
        return registerService.getAllDoctorsByDepartment(department_id);
    }

    @RequestMapping("/submit")
    public String registerSubmit(@RequestBody Register register){
        if(registerService.addRegisterAndBill(register)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/print-bill")
    public String printInvoice(@RequestBody Bill bill){
        //打印发票
        if(registerService.addBill(bill)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    //退号操作
    @RequestMapping("/refund")
    public String registerRefund(@RequestBody Integer register_id){
        if(registerService.refund(register_id)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }




}
