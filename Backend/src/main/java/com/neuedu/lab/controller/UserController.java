package com.neuedu.lab.controller;


import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.Doctor;
import com.neuedu.lab.model.po.User;
import com.neuedu.lab.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/get-all-user")
//    显示所有用户信息
    public JSONObject getAllUsers(){
        return ConstantUtils.responseSuccess(userService.getAllUsers());
    }

    @RequestMapping("/get-all-doctor")
//    显示所有医生信息
    public JSONObject getAllDoctors(){
        return ConstantUtils.responseSuccess(userService.getAllDoctors());
    }

    @RequestMapping("/get")
//    查询某一用户/医生信息
    public JSONObject getUser(@RequestBody JSONObject request){
        if(request.getString("user_id") != null){
            if(userService.getUser(request.getString("user_id")) != null){
                return ConstantUtils.responseSuccess(userService.getUser(request.getString("user_id")));
            }else{
                return ConstantUtils.responseFail(null);
            }
        }else{
            if(userService.getDoctor(request.getString("doctor_id")) != null){
                return ConstantUtils.responseSuccess(userService.getDoctor(request.getString("doctor_id")));
            }else{
                return ConstantUtils.responseFail(null);
            }

        }
    }

    @RequestMapping("/check-valid")
    //检验登录
    public JSONObject checkValid(@RequestBody JSONObject request){
        if(request.getString("user_account") != null){
            return userService.checkUserValid(request.getString("user_account"),request.getString("user_password"));
        }
        else{
            return userService.checkDoctorValid(request.getString("doctor_account"),request.getString("doctor_password"));
        }
    }

    @RequestMapping("/add-user")
    public JSONObject addUser(@RequestBody User user){
        if(userService.addUser(user)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/add-doctor")
    public JSONObject addDoctor(@RequestBody Doctor doctor){
        if(userService.addDoctor(doctor)){
            return ConstantUtils.responseSuccess(null);
        }else{
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/delete-user")
    public JSONObject deleteUser(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(userService.deleteUser(request.getString("user_account")));
    }

    @RequestMapping("/delete-doctor")
    public JSONObject deleteDoctor(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(userService.deleteDoctor(request.getString("doctor_account")));
    }

    @RequestMapping("/update-user")
    public JSONObject updateUser(@RequestBody User user){
        if(userService.updateUser(user)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update-user-password")
    public JSONObject updateUserPassword(@RequestBody User user){
        if(userService.updateUserPassword(user)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update-doctor")
    public JSONObject updateDoctor(@RequestBody Doctor doctor){
        if(userService.updateDoctor(doctor)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update-doctor-password")
    public JSONObject updateDoctorPassword(@RequestBody Doctor doctor){
        if(userService.updateDoctorPassword(doctor)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }
}