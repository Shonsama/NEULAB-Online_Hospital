package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.UserMapper;
import com.neuedu.lab.model.po.Doctor;
import com.neuedu.lab.model.po.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    /*获取所有用户信息*/
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public User getUser(String user_id) {
        return userMapper.getUser(user_id);
    }

    /*检验登录*/
    public JSONObject checkUserValid(String user_account, String user_password) {
        User user = userMapper.getUserByAccount(user_account);
        if (user.getUser_password().equals(user_password)) {
            return ConstantUtils.responseSuccess("success", ConstantUtils.generateToken(user.getUser_id(), ConstantDefinition.USER_TYPE[0]));
        } else {
            return ConstantUtils.responseFail("wrongPassword", null);
        }
    }

    public boolean addUser(User user) {
        try{
            userMapper.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteUser(String user_account) {
        try{
            userMapper.deleteUser(user_account);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
            return true;
    }

    public boolean updateUser(User user) {
        try{
            userMapper.updateUser(user);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateUserPassword(User user) {
        try{
            userMapper.updateUserPassword(user);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
            return true;
    }

    /*获取所有医生信息*/
    public List<Doctor> getAllDoctors() {
        return userMapper.getAllDoctors();
    }

    public Doctor getDoctor(String doctor_id) {
        return userMapper.getDoctor(doctor_id);
    }


    /*检验医生登录*/
    public JSONObject checkDoctorValid(String doctor_account, String doctor_password) {
        Doctor doctor = userMapper.getDoctorByAccount(doctor_account);
        if (doctor.getDoctor_password().equals(doctor_password)) {
            return ConstantUtils.responseSuccess("success", ConstantUtils.generateToken(doctor.getDoctor_id(),ConstantDefinition.USER_TYPE[1]));
        } else {
            return ConstantUtils.responseFail("wrongPassword", null);
        }
    }

    public boolean addDoctor(Doctor doctor) {
        try{
            userMapper.addDoctor(doctor);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
            return true;
    }

    public boolean deleteDoctor(String doctor_account) {
        try{
            userMapper.deleteDoctor(doctor_account);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateDoctor(Doctor doctor) {
        try{
            userMapper.updateDoctor(doctor);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
            return true;
    }

    public boolean updateDoctorPassword(Doctor doctor) {
        try{
            userMapper.updateDoctorPassword(doctor);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
            return true;
    }
}
