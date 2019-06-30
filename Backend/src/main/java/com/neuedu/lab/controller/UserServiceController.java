package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.utils.ConstantUtils;
import com.neuedu.lab.model.po.Daily;
import com.neuedu.lab.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日结是系统管理员的接口， 退费是收费员的接口
 */
@RestController
@RequestMapping("/user-service")
public class UserServiceController {
    @Autowired
    private UserService userService;

    //日结
    //日结查询
    @RequestMapping("/daily-search")
    public JSONObject dailySearch(@RequestBody Daily daily) {
        return userService.dailySearch(daily);
    }

    //日结结算
    @RequestMapping("/daily-submit")
    public JSONObject dailySubmit(@RequestBody JSONObject request) {
        return ConstantUtils.responseSuccess(userService.dailySubmit(request.getInteger("daily_id")));
    }

    //日结查询
    @RequestMapping("/daily-get")
    public JSONObject dailyGet(@RequestBody Daily daily) {
        return userService.dailyGet(daily);
    }

    //日结发票查询
    @RequestMapping("/daily-bill-get")
    public JSONObject dailyBillGet(@RequestBody JSONObject request) {
        return userService.getDailyBill(request.getInteger("daily_id"));
    }

    //日结审核通过
    @RequestMapping("/daily-pass")
    public JSONObject dailyPass(@RequestBody JSONObject request) {
        return userService.dailyPass(request.getInteger("daily_id"),
                request.getInteger("daily_owner_id"));
    }


    //退号部分
    @RequestMapping("/refund/get-paid-registers")
    public JSONObject getPaidRegisters(@RequestBody JSONObject request) {
        return userService.getPaidRegisters(request.getInteger("patient_id"));
    }


    //退费部分
    @RequestMapping("/refund")
    public JSONObject refund(@RequestBody JSONObject request) {
        return userService.refund(request.getString("type"), request.getInteger("id"));
    }

    // 退药
    //获取一个处方的所有内容
    @RequestMapping("/refund/prescription/get-content")
    public JSONObject getPrescriptionContent(@RequestBody JSONObject request) {
        return userService.getPrescriptions(request.getInteger("prescription_id"));
    }

    @RequestMapping("/refund/return-prescription")
    public JSONObject returnPrescription(@RequestBody JSONObject request) {
        return userService.returnMedicine(request.getInteger("prescription_id"),
                request.getInteger("prescription_content_id"), request.getInteger("prescription_num"));
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
