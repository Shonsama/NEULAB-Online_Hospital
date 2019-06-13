package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.po.Bill;
import com.neuedu.lab.model.po.Register;
import com.neuedu.lab.model.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 给挂号员的接口
 */
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;


    @RequestMapping("/get-all-doctor")
    public JSONObject getAllDoctorsByDepartment(@RequestBody JSONObject request){
        return registerService.getAllDoctorsByDepartment(
                request.getString("department_id"),request.getInteger("register_level_id"));
    }

    @RequestMapping("/submit")
    public JSONObject registerSubmit(@RequestBody Register register){
        return registerService.addRegister(register);
    }

    @RequestMapping("/print-bill")
    public JSONObject printInvoice(@RequestBody Bill bill){
        //打印发票
        return registerService.addBill(bill);
    }

    //退号操作
    @RequestMapping("/refund")
    public JSONObject registerRefund(@RequestBody JSONObject request){
        return registerService.refund(request.getInteger("register_id"));
    }




}
