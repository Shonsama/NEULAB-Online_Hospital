package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private BillMapper billMapper;
    @Resource
    private DailyMapper dailyMapper;
    @Resource
    private MedicalSkillMapper medicalSkillMapper;
    @Resource
    private PrescriptionMapper prescriptionMapper;
    @Resource
    private PrescriptionContentMapper prescriptionContentMapper;

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

    //缴费部分


    //获取该挂号ID下所有缴费项目
    public JSONObject getMedicalSkillFee(Integer register_id){
        List<MedicalSkill> medicalSkills;
        try{
            medicalSkills = medicalSkillMapper.getMedicalSkillByRegisterId(register_id, ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[1]);
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("获取失败",null);
        }
        return ConstantUtils.responseSuccess(medicalSkills);
    }


    //打印发票
    public JSONObject printBill(List<Bill> billList){
        try{
            for(Bill bill:billList){
                billMapper.addBill(bill);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseSuccess(null);
        }
        return  ConstantUtils.responseSuccess(null);
    }




    //医技项目退费
    @Transactional
    public JSONObject refundMedicalSkill(Integer medical_skill_id){
        try {
            medicalSkillMapper.updateMedicalSkillState(medical_skill_id,ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[5]);
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("更新项目状态过程出错",null);
        }
        //获取原发票
        Bill bill;
        try {
            bill = billMapper.getBillByMedicalSkillId(medical_skill_id);
            if(bill==null){
                return ConstantUtils.responseFail("无对应项目发票",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("获取原发票过程出错",null);
        }


        //增加对冲发票
        bill.setBill_sum(ConstantUtils.convertToNegtive(bill.getBill_sum()));
        bill.setBill_actual_sum(ConstantUtils.convertToNegtive(bill.getBill_actual_sum()));
        try{
            billMapper.addBill(bill);
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("增加对冲发票过程出错",null);
        }
        return ConstantUtils.responseSuccess(bill);
    }


    //处方所有药物全部退费
    @Transactional
    public JSONObject refundPrescription(Integer prescription_id){
        List<PrescriptionContent> contentList;
        try {
            contentList = prescriptionContentMapper.getPrescriptionContents(prescription_id);
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("获取处方药品记录失败",null);
        }
        try{
            for(PrescriptionContent content: contentList){ //对一个处方的每种药 分别统计其数量并加入数量相反的药品记录
                content.setPrescription_num(ConstantUtils.convertToNegtive(content.getPrescription_num()));
                content.setPrescription_content_fee(ConstantUtils.convertToNegtive(content.getPrescription_unit_price().multiply(new BigDecimal(content.getPrescription_num()))));
                prescriptionContentMapper.addPrescriptionContent(content);
                }
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("插入退费记录失败",null);
        }

        //插入发票对冲记录
        Bill billBefore;
        try{
            billBefore = billMapper.getBillByPrescriptionId(prescription_id);
            billBefore.setBill_actual_sum(ConstantUtils.convertToNegtive(billBefore.getBill_actual_sum()));
            billBefore.setBill_sum(ConstantUtils.convertToNegtive(billBefore.getBill_sum()));
            billMapper.addBill(billBefore);
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("插入发票对冲记录出错",null);
        }
        return ConstantUtils.responseSuccess(billBefore);
    }

    //退一定数量的处方的某种药
    @Transactional
    public JSONObject refundPrescriptionContent(Integer prescription_id,Integer prescription_medicine_id, Integer prescription_num){
        PrescriptionContent content;
        //获取处方药品记录
        try {
            content = prescriptionContentMapper.getPrescriptionContent(prescription_id,prescription_medicine_id);
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("获取处方药品记录失败",null);
        }

        if(content.getPrescription_num()<prescription_num){
            return  ConstantUtils.responseFail("退药数量不能大于原购买数量",null);
        }

        //增加一条对冲记录

        //将此条记录的数量变负， 总费用也相应变负
        content.setPrescription_num(ConstantUtils.convertToNegtive(prescription_num));

        content.setPrescription_content_fee(content.getPrescription_unit_price().multiply(new BigDecimal(prescription_num)));

        try{
            prescriptionContentMapper.addPrescriptionContent(content);
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("插入药品对冲记录出错",null);
        }

        //插入发票对冲记录

        Bill bill;
        try{
            Bill billBefore = billMapper.getBillByPrescriptionId(prescription_id);
            bill = billMapper.getBillByPrescriptionId(prescription_id);
            billBefore.setBill_actual_sum(ConstantUtils.convertToNegtive(billBefore.getBill_actual_sum()));
            billBefore.setBill_sum(ConstantUtils.convertToNegtive(billBefore.getBill_sum()));
            billMapper.addBill(billBefore);
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("插入发票对冲记录出错",null);
        }


        //插入新发票记录
        try {
            bill.setBill_sum(bill.getBill_sum().add(content.getPrescription_content_fee()));
            bill.setBill_actual_sum(bill.getBill_actual_sum().add(content.getPrescription_content_unit_actual_price().multiply(new BigDecimal(prescription_num))));
            billMapper.addBill(bill);
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("插入新发票记录出错",null);
        }
        return ConstantUtils.responseSuccess(bill);
    }


}
