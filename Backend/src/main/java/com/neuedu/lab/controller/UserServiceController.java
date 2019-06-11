package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.Daily;
import com.neuedu.lab.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * 日结是系统管理员的接口， 退费是收费员的接口
 */
@RestController
@RequestMapping("/user-service")
public class UserServiceController {
    @Autowired
    private UserService userService;
    //日结
    @RequestMapping("/daily-clear")
    public JSONObject dailyClear(@RequestBody Daily daily) throws ParseException {
        return ConstantUtils.responseSuccess(userService.dailyClear(daily));
    }

    @RequestMapping("/daily-get")
    public JSONObject dailyGet(@RequestBody Daily daily){
        return userService.dailyGet(daily);
    }

    @RequestMapping("/daily-pass")
    public JSONObject dailyPass(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(userService.dailyPass(request.getInteger("daily_id")));
    }



    //退号部分
    @RequestMapping("/refund/get-paid-registers")
    public JSONObject getPaidRegisters(@RequestBody JSONObject request){
        return userService.getPaidRegisters(request.getInteger("patient_id"));
    }



    //退费部分
    @RequestMapping("/refund")
    public JSONObject refund(@RequestBody JSONObject request){
        return userService.refund(request.getString("type"),request.getInteger("id"));
    }
    // 退药
    @RequestMapping("/refund/return-prescription")
    public JSONObject retrunPrescription(@RequestBody JSONObject request){
        return userService.returnMedicine(request.getInteger("prescription_id"),
                request.getString("prescription_medicine_id"),request.getInteger("prescription_num"));
    }

    //以下两个注释接口已经弃用，转为上面refund接口，下面两个接口准备删除
//    @RequestMapping("/refund/medical-skill")
//    public JSONObject refundMedicalSkill(@RequestBody JSONObject request){
//        return userService.refundMedicalSkill(request.getInteger("medical_skill_id"));
//    }
//
//    @RequestMapping("/refund/prescription")
//    public JSONObject refundPrescription(@RequestBody JSONObject request){
//        return userService.refundPrescription(request.getInteger("prescription_id"));
//    }



}
