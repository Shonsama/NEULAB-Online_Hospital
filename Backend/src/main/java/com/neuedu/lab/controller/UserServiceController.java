package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.Daily;
import com.neuedu.lab.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public JSONObject dailyClear(Daily daily) throws ParseException {
        return ConstantUtils.responseSuccess(userService.dailyClear(daily));
    }

    @RequestMapping("/daily-pass")
    public JSONObject dailyPass(Integer daily_id){
        return ConstantUtils.responseSuccess(userService.dailyPass(daily_id));
    }

}
