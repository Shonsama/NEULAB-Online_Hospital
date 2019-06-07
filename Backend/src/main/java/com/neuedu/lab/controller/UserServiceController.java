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



    //退费部分
    @RequestMapping("/refund/medical-skill")
    public JSONObject refundMedicalSkill(@RequestBody JSONObject request){
        return userService.refundMedicalSkill(request.getInteger("medical_skill_id"));
    }

    @RequestMapping("/refund/prescription")
    public JSONObject refundPrescription(@RequestBody JSONObject request){
        return userService.refundPrescription(request.getInteger("prescription_id"));
    }

}
