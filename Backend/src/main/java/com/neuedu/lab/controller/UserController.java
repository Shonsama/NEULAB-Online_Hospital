package com.neuedu.lab.controller;


import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.po.User;
import com.neuedu.lab.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/get-all")
//    显示所有用户信息
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping("/get")
//    查询某一用户信息
    public List<User> getUser(@RequestBody JSONObject user_account){
        String getString = user_account.getString("user_account");
        return userService.getUser(getString);
    }

    @RequestMapping("/check-valid")
    //检验登录
    public String checkValid(@RequestBody String user_account, @RequestBody String user_password){
        return userService.checkValid(user_account,user_password);
    }

    @RequestMapping("/add")
    public String addUser(@RequestBody User user){
        if(userService.addUser(user)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestBody JSONObject user_account){
        String getString = user_account.getString("user_account");
        if(userService.deleteUser(getString)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/update")
    public String updateUser(@RequestBody User user){
        if(userService.updateUser(user)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/update-password")
    public String updateUserPassword(@RequestBody User user){
        if(userService.updateUserPassword(user)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }
}