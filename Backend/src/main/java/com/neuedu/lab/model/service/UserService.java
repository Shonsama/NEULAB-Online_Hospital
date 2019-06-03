package com.neuedu.lab.model.service;

import com.neuedu.lab.model.mapper.UserMapper;
import com.neuedu.lab.model.po.Doctor;
import com.neuedu.lab.model.po.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    /*获取所有用户信息*/
    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }

    public List<User> getUser(String user_account){
        return userMapper.getUser(user_account);
    }

    public boolean checkUserIfExist(String user_account){
        if(userMapper.getUser(user_account).size() == 0){//没有该用户
            System.out.println("没有该用户");
            return false;
        }else {
            System.out.println("有该用户");
            return true;
        }
    }

    /*检验登录*/
    public String checkUserValid(String user_account, String user_password){
        if(checkUserIfExist(user_account) == false){//没有该用户
            return "noUser";
        }else{
            User user = userMapper.getUser(user_account).get(0);
            if(user.getUser_password().equals(user_password)){
                return "success";
            }
            else{
                return "wrongPassword";
            }
        }
    }

    public boolean addUser(User user){
        String user_account = user.getUser_account();
        if(checkUserIfExist(user_account) == false){
            userMapper.addUser(user);
            return true;
        }
        else{
            return false;//存在相同account的用户，不允许
        }

    }

    public boolean deleteUser(String user_account){
        if(checkUserIfExist(user_account) == false){//没有该用户
            return false;
        }else{
            userMapper.deleteUser(user_account);
            return true;
        }

    }

    public boolean updateUser(User user){
        String user_account = user.getUser_account();
        if(checkUserIfExist(user_account) == false){//没有该用户
            return false;
        }else{
            userMapper.updateUser(user);
            return true;
        }

    }

    public boolean updateUserPassword(User user){
        String user_account = user.getUser_account();
        if(checkUserIfExist(user_account) == false){//没有该用户
            return false;
        }else{
            userMapper.updateUserPassword(user);
            return true;
        }
    }

    /*获取所有医生信息*/
    public List<Doctor> getAllDoctors(){
        return userMapper.getAllDoctors();
    }

    public List<Doctor> getDoctor(String doctor_account){
        return userMapper.getDoctor(doctor_account);
    }

    public boolean checkDoctorIfExist(String doctor_account){
        if(userMapper.getDoctor(doctor_account).size() == 0){//没有该用户
            System.out.println("没有该医生");
            return false;
        }else {
            System.out.println("有该医生");
            return true;
        }
    }

    /*检验医生登录*/
    public String checkDoctorValid(String doctor_account, String doctor_password){
        if(checkDoctorIfExist(doctor_account) == false){//没有该用户
            return "noDoctor";
        }else{
            Doctor doctor = userMapper.getDoctor(doctor_account).get(0);
            if(doctor.getDoctor_password() == doctor_password){
                return "success";
            }
            else{
                return "wrongPassword";
            }
        }
    }

    public boolean addDoctor(Doctor doctor){
        String doctor_account = doctor.getDoctor_account();
        if(checkDoctorIfExist(doctor_account) == false){
            userMapper.addDoctor(doctor);
            return true;
        }
        else{
            return false;//存在相同account的用户，不允许
        }
    }

    public boolean deleteDoctor(String doctor_account){
        if(checkDoctorIfExist(doctor_account) == false){//没有该用户
            return false;
        }else{
            userMapper.deleteDoctor(doctor_account);
            return true;
        }

    }

    public boolean updateDoctor(Doctor doctor){
        String doctor_account = doctor.getDoctor_account();
        if(checkDoctorIfExist(doctor_account) == false){//没有该用户
            return false;
        }else{
            userMapper.updateDoctor(doctor);
            return true;
        }

    }

    public boolean updateDoctorPassword(Doctor doctor){
        String doctor_account = doctor.getDoctor_account();
        if(checkUserIfExist(doctor_account) == false){//没有该用户
            return false;
        }else{
            userMapper.updateDoctorPassword(doctor);
            return true;
        }
    }


}
