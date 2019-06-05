package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.BillMapper;
import com.neuedu.lab.model.mapper.DailyMapper;
import com.neuedu.lab.model.mapper.UserMapper;
import com.neuedu.lab.model.po.Bill;
import com.neuedu.lab.model.po.Daily;
import com.neuedu.lab.model.po.Doctor;
import com.neuedu.lab.model.po.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private BillMapper billMapper;
    @Resource
    private DailyMapper dailyMapper;

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

    //用户服务

    //日结
    @Transactional
    public List<Bill> dailyClear(Daily daily) throws ParseException {
        Daily dailyRecent = dailyMapper.getDailyByUserId(daily.getDaily_user_id()); //取出最后一次日结记录
        Date end_time = dailyRecent==null? ConstantUtils.getSystemInitializeTime():dailyRecent.getDaily_end(); //若无记录，则按系统初始时间作为上次结束时间
        daily.setDaily_start(end_time); //将上次的结束时间作为本次的初始时间
        daily.setDaily_pass(false);  //默认审核未通过
        dailyMapper.addDaily(daily); //将记录插入到日结表
        System.out.println(daily.getDaily_start());
        System.out.println(daily.getDaily_end());
        return billMapper.getBillByUserIdAndTime(daily.getDaily_user_id(), daily.getDaily_start(), daily.getDaily_end());
    }

    //日结确认
    public boolean dailyPass(Integer daily_id){
        try{
            dailyMapper.updateDailyPass(daily_id,true);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //获取日结信息
    public JSONObject dailyGet(Daily daily){

        try{
            return ConstantUtils.responseSuccess(dailyMapper.getDaily(daily)) ;
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail(null);
        }
    }
}
