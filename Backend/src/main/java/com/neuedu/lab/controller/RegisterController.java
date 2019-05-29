package com.neuedu.lab.controller;

import com.neuedu.lab.model.po.*;
import com.neuedu.lab.model.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @RequestMapping("/get-all-department")
    public List<Department> getAllDepartment(){
        return registerService.getAllDepartments();
    }

    @RequestMapping("/get-all-doctor")
    public List<Doctor> getAllDoctor(@RequestParam String department_id){
        return registerService.getAllDoctors(department_id);
    }

    @RequestMapping("/submit")
    public String registerSubmit(@RequestParam Register register){
        if(registerService.addRegister(register)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/print-bill")
    public String printInvoice(@RequestParam Bill bill){
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
    public String registerRefund(@RequestParam Integer register_id){
        if(registerService.refund(register_id)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }




}
