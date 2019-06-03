package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.Bill;
import com.neuedu.lab.model.po.Register;
import com.neuedu.lab.model.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public JSONObject getAllDoctorsByDepartment(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(registerService.getAllDoctorsByDepartment(request.getString("department_id")));
    }

    @RequestMapping("/submit")
    public JSONObject registerSubmit(@RequestBody Register register){
        if(registerService.addRegister(register)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/print-bill")
    public JSONObject printInvoice(@RequestBody Bill bill){
        //打印发票
        if(registerService.addBill(bill)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //退号操作
    @RequestMapping("/refund")
    public JSONObject registerRefund(@RequestBody JSONObject request){
        if(registerService.refund(request.getInteger("register_id"))){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }




}
