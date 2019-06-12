package com.neuedu.lab.model.service;


import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.*;
import org.eclipse.jdt.internal.compiler.lookup.SourceTypeBinding;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.neuedu.lab.Utils.ConstantDefinition.*;
import static com.neuedu.lab.Utils.ConstantUtils.responseFail;
import static com.neuedu.lab.Utils.ConstantUtils.responseSuccess;

/**
 * @author wp
 * 主要提供收费员的接口
 * 包括日结、退号、退费
 */


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
    @Resource
    private RegisterMapper registerMapper;
    @Resource
    private PatientMapper patientMapper;

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
            //return ConstantUtils.responseSuccess("success", ConstantUtils.generateToken(user.getUser_id(), ConstantDefinition.USER_TYPE[0]));
            return ConstantUtils.responseSuccess("success",user);
        } else {
            return ConstantUtils.responseFail("wrongPassword", null);
        }
    }

    public boolean addUser(User user) {
        try {
            userMapper.addUser(user);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteUser(String user_account) {
        try {
            userMapper.deleteUser(user_account);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateUser(User user) {
        try {
            userMapper.updateUser(user);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateUserPassword(User user) {
        try {
            userMapper.updateUserPassword(user);
        } catch (RuntimeException e) {
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
            //return ConstantUtils.responseSuccess("success", ConstantUtils.generateToken(doctor.getDoctor_id(), ConstantDefinition.USER_TYPE[1]));
            return ConstantUtils.responseSuccess("success",doctor);
        } else {
            return ConstantUtils.responseFail("wrongPassword", null);
        }
    }

    public boolean addDoctor(Doctor doctor) {
        try {
            userMapper.addDoctor(doctor);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteDoctor(String doctor_account) {
        try {
            userMapper.deleteDoctor(doctor_account);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateDoctor(Doctor doctor) {
        try {
            userMapper.updateDoctor(doctor);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateDoctorPassword(Doctor doctor) {
        try {
            userMapper.updateDoctorPassword(doctor);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //用户服务

    //收费员自己日结
    @Transactional
    public JSONObject dailySearch(Daily daily)  {
        try{
            dailyMapper.deleteNonPassDaily(daily.getDaily_user_id(),DAILY_PASS_STATE[0]);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail("删除“未提交”日结记录出错",null);
        }
        Daily dailyRecent;
        try{
            dailyRecent = dailyMapper.getDailyByUserId(daily.getDaily_user_id(),DAILY_PASS_STATE[1],DAILY_PASS_STATE[2],DAILY_PASS_STATE[3]); //取出最后一次日结记录
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail("获取上一次日结记录出错",null);
        }


        Date end_time = null; //若无记录，则按系统初始时间作为上次结束时间
        try {
            end_time = dailyRecent == null ? ConstantUtils.getSystemInitializeTime() : dailyRecent.getDaily_end();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        daily.setDaily_start(end_time); //将上次的结束时间作为本次的初始时间
        daily.setDaily_pass_state(ConstantDefinition.DAILY_PASS_STATE[0]);
        if(daily.getDaily_end()==null){
            daily.setDaily_end(new Date());
        }


        //获取发票List并将其冻结
        List<Bill> bills;
        try{
           bills = billMapper.getBillByUserIdAndTime(daily.getDaily_user_id(), daily.getDaily_start(), daily.getDaily_end());
           billMapper.updateBillFrozen(daily.getDaily_user_id(), daily.getDaily_start(), daily.getDaily_end());
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail("获取发票List",null);
        }

        //通过发票计算费用
        BigDecimal daily_sum = new BigDecimal(0);
        BigDecimal daily_actual_sum = new BigDecimal(0);
        BigDecimal daily_register_sum = new BigDecimal(0);
        BigDecimal daily_mid_prescription_sum = new BigDecimal(0);
        BigDecimal daily_west_prescription_sum = new BigDecimal(0);
        BigDecimal daily_check_sum = new BigDecimal(0);
        BigDecimal daily_examine_sum = new BigDecimal(0);
        BigDecimal daily_handle_sum = new BigDecimal(0);

        for(Bill bill: bills){
            if(bill.getBill_type().equals(ConstantDefinition.BILL_TYPE[0])){//挂号费
                daily_register_sum = daily_register_sum.add(bill.getBill_sum());
            }
            else if(bill.getBill_type().equals(ConstantDefinition.BILL_TYPE[4])){ //中药费
                daily_mid_prescription_sum = daily_mid_prescription_sum.add(bill.getBill_sum());
            }
            else if(bill.getBill_type().equals(ConstantDefinition.BILL_TYPE[5])){ //西药费
                daily_west_prescription_sum = daily_west_prescription_sum.add(bill.getBill_sum());
            }
            else if(bill.getBill_type().equals(ConstantDefinition.BILL_TYPE[1])){ //检查费
                daily_check_sum = daily_check_sum.add(bill.getBill_sum());
            }
            else if(bill.getBill_type().equals(ConstantDefinition.BILL_TYPE[2])){ //检验费
                daily_examine_sum = daily_examine_sum.add(bill.getBill_sum());
            }
            else if(bill.getBill_type().equals(ConstantDefinition.BILL_TYPE[3])){ //处置费
                daily_handle_sum = daily_handle_sum.add(bill.getBill_sum());
            }else {
                return responseFail("出现未知类型");
            }

            daily_sum = daily_sum.add(bill.getBill_sum());
        }

        daily.setDaily_sum(daily_sum);
        daily.setDaily_actual_sum(daily_actual_sum);
        daily.setDaily_register_sum(daily_register_sum);
        daily.setDaily_mid_prescription_sum(daily_mid_prescription_sum);
        daily.setDaily_west_prescription_sum(daily_west_prescription_sum);

        try{
            dailyMapper.addDaily(daily); //将记录插入到日结表
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail("将记录插入到日结表出错",null);
        }

        //给日结表填充发票记录
        daily.setBills(bills);

        return responseSuccess(daily);
    }
    //日结确认
    public JSONObject dailySubmit(Integer daily_id){
        try{
            dailyMapper.updateDailyPass(daily_id,DAILY_PASS_STATE[1],null);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(dailyMapper.getDailyById(daily_id));
    }

    //日结审核通过
    public JSONObject dailyPass(Integer daily_id,Integer daily_owner_id) {
        try {
            dailyMapper.updateDailyPass(daily_id, DAILY_PASS_STATE[2],daily_owner_id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(dailyMapper.getDailyById(daily_id));
    }

    //获取日结信息
    public JSONObject dailyGet(Daily daily) {
        try {
            return ConstantUtils.responseSuccess(dailyMapper.getDaily(daily));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ConstantUtils.responseFail(null);
        }
    }

    //获取日结发票信息
    public JSONObject getDailyBill(Integer daily_id){
        Daily daily;
        try{
            daily = dailyMapper.getDailyById(daily_id);
            daily.setBills(billMapper.getBillByUserIdAndTime(daily.getDaily_user_id(),daily.getDaily_start(),daily.getDaily_end()));
            List<Bill> abandonBillList = new ArrayList<>();//作废
            List<Bill> redoBillList = new ArrayList<>();//重打
            List<Bill> overprintBillList = new ArrayList<>();//补打
            List<Bill> flushBillList = new ArrayList<>();//对冲
            for(Bill bill: daily.getBills()){
                if(bill.getBill_type().equals(BILL_STATE[1])){
                    abandonBillList.add(bill);
                }
                else if(bill.getBill_type().equals(BILL_STATE[2])){
                    redoBillList.add(bill);
                }
                else if(bill.getBill_type().equals(BILL_STATE[3])){
                    overprintBillList.add(bill);
                }
                else if(bill.getBill_type().equals(BILL_STATE[4])){
                    flushBillList.add(bill);
                }
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(daily);
    }

    //缴费部分


    //获取该挂号ID下所有缴费项目
    public JSONObject getMedicalSkillFee(Integer register_id) {
        List<MedicalSkill> medicalSkills;
        try {
            medicalSkills = medicalSkillMapper.getMedicalSkillByRegisterId(register_id, ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[1]);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ConstantUtils.responseFail("获取失败", null);
        }
        return ConstantUtils.responseSuccess(medicalSkills);
    }

    //收费
    @Transactional
    public JSONObject payMedicalSkillFee(List<Integer> medical_skill_ids) {
        try {
            for (Integer medical_skill_id : medical_skill_ids) {
                medicalSkillMapper.updateMedicalSkillState(medical_skill_id, ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[3],null);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ConstantUtils.responseFail("收费失败", null);
        }
        return ConstantUtils.responseSuccess(null);
    }

    //打印发票
    public JSONObject printBill(List<Bill> billList) {
        try {
            for (Bill bill : billList) {
                billMapper.addBill(bill);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ConstantUtils.responseSuccess(null);
        }
        return ConstantUtils.responseSuccess(null);
    }


    //退号， 获取一个病人下所有“已挂号”状态下的挂号记录
    public JSONObject getPaidRegisters(Integer patient_id){
        List<Register> registers;
        //获取挂号记录
        try{
            registers = registerMapper.getRegistersByPatientId(patient_id);
        }catch (RuntimeException e ){
            e.printStackTrace();
            return responseFail("获取挂号记录失败",null);
        }
        Iterator<Register> iterator = registers.iterator();
        while (iterator.hasNext()){
            //如果不是“已挂号”状态，则删除
            if(!iterator.next().getRegister_info_state().equals(ConstantDefinition.REGISTER_STATE[0])){
                iterator.remove();
            }
        }
        //填充病人
        try{
            for(Register register : registers){
                register.setPatient(patientMapper.getPatientByRecordId(patient_id));
            }
        }catch (RuntimeException e ){
            e.printStackTrace();
            return responseFail("填充挂号记录的病人失败",null);
        }
        return responseSuccess(registers);
    }


    //医技项目退费
    @Transactional
    public JSONObject refundMedicalSkill(Integer medical_skill_id) {
        //获取原发票
        Bill bill;
        try {
            List<Bill> bills = billMapper.getBillByMedicalSkillId(medical_skill_id);
            if (bills.size() == 0) {
                return ConstantUtils.responseFail("无对应项目发票", null);
            } else if (bills.size() > 1) {
                return ConstantUtils.responseFail("项目发票不唯一，可能已拥有退费发票", null);
            } else {
                bill = bills.get(0);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ConstantUtils.responseFail("获取原发票过程出错", null);
        }

        try {
            MedicalSkill medicalSkilBefore = medicalSkillMapper.getMedicalSkill(medical_skill_id);
            //如果医技项目不是已缴费状态，不能退费
            if (!medicalSkilBefore.getMedical_skill_execute_state().equals(ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[3])) {
                return ConstantUtils.responseFail("当前医技项目状态为["
                        + medicalSkilBefore.getMedical_skill_execute_state() + "],不可退费", null);
            } else {
                medicalSkillMapper.updateMedicalSkillState(medical_skill_id, ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[5],null);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ConstantUtils.responseFail("更新项目状态过程出错", null);
        }

        //增加对冲发票
        bill.setBill_sum(ConstantUtils.convertToNegtive(bill.getBill_sum()));
        bill.setBill_sum(ConstantUtils.convertToNegtive(bill.getBill_sum()));
        try {
            billMapper.addBill(bill);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ConstantUtils.responseFail("增加对冲发票过程出错", null);
        }
        return ConstantUtils.responseSuccess(bill);
    }

    // 未领药直接退药，由收费员操作
    @Transactional
    public JSONObject returnMedicine(Integer prescription_id, String prescription_medicine_id, Integer prescription_num) {
        Prescription prescription = prescriptionMapper.getPrescription(prescription_id);

        PrescriptionContent prescriptionContent = new PrescriptionContent();
        prescriptionContent.setPrescription_id(prescription_id);
        prescriptionContent.setPrescription_medicine_id(prescription_medicine_id);
        prescriptionContent.setPrescription_num(prescription_num);

        //首先查看此条药品记录是否存在 根据处方ID和药物ID（不能根据药品记录ID）
        PrescriptionContent prescriptionContentBefore = prescriptionContentMapper.getPrescriptionContentPositive(prescriptionContent);

        //查看药品状态应该是已缴费状态
        if (!prescription.getPrescription_execute_state().equals(ConstantDefinition.PRESCRIPTION_EXECUTE_STATE[3])) {
            return ConstantUtils.responseFail("该处方状态为[" + prescription.getPrescription_execute_state() + "],不可退药", null);
        }

        //查看药品数量是否满足
        if (prescriptionContentBefore.getPrescription_refund_available_num() < prescriptionContent.getPrescription_num()) {
            return ConstantUtils.responseFail("药品数量大于可退费数量", null);
        } else {
            try {
                //修改可退药数量
                prescriptionContentBefore.setPrescription_refund_available_num(
                        prescriptionContentBefore.getPrescription_refund_available_num() - prescriptionContent.getPrescription_num()
                );

                //修改金额
                prescriptionContentBefore.setPrescription_content_fee(
                        prescriptionContentBefore.getPrescription_unit_price().multiply
                                (new BigDecimal(prescriptionContentBefore.getPrescription_refund_available_num())));


                //更新其可退药数量和金额
                prescriptionContentMapper.updatePrescriptionContent(prescriptionContentBefore);


                //再次获取最初的药品记录
                PrescriptionContent prescriptionContentToAdd = prescriptionContentMapper.getPrescriptionContentPositive(prescriptionContent);

                //改变数量
                prescriptionContentToAdd.setPrescription_num(ConstantUtils.convertToNegtive(prescriptionContent.getPrescription_num()));


                //增加退药的数量为负的药品记录
                prescriptionContentMapper.addPrescriptionContent(prescriptionContentToAdd);

                //更改原处方状态为已退药,更新药费
                prescription.setPrescription_execute_state(PRESCRIPTION_EXECUTE_STATE[5]);
                prescription.setPrescription_fee(prescription.getPrescription_fee().subtract(
                        prescriptionContentBefore.getPrescription_unit_price().multiply(new BigDecimal(prescriptionContent.getPrescription_num()))));
                prescriptionMapper.updatePrescription(prescription);
            } catch (RuntimeException e) {
                e.printStackTrace();
                return responseFail("退药失败");
            }
            return responseSuccess(prescriptionContentMapper.getPrescriptionContentPositive(prescriptionContent));
        }
    }


    //根据退药记录退钱
    @Transactional
    public JSONObject refundPrescription(Integer prescription_id) {
        //首先检测处方已经处于退药状态
        Prescription prescription = prescriptionMapper.getPrescription(prescription_id);
        if (prescription == null) {
            return ConstantUtils.responseFail("不存在该处方", null);
        } else if (!prescription.getPrescription_execute_state().equals(ConstantDefinition.PRESCRIPTION_EXECUTE_STATE[5])) {
            return ConstantUtils.responseFail("该处方状态为["
                    + prescription.getPrescription_execute_state() + "],不可退费", null);
        }

        //插入发票对冲记录
        Bill billBefore;
        try {
            billBefore = billMapper.getBillByPrescriptionId(prescription_id);
            billBefore.setBill_sum(ConstantUtils.convertToNegtive(billBefore.getBill_sum()));
            billBefore.setBill_state(BILL_STATE[4]);
            billMapper.addBill(billBefore);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ConstantUtils.responseFail("插入发票对冲记录出错", null);
        }

        //获取原处方记录
        Prescription prescriptionToAdd = prescriptionMapper.getPrescription(prescription_id);

        //重新计算处方费用
        List<PrescriptionContent> contentList;
        BigDecimal sum = new BigDecimal(0);
        try {
            contentList = prescriptionContentMapper.getPrescriptionContentsPositive(prescription_id);
            System.out.println("[dddd]"+contentList.size());
            for (PrescriptionContent content : contentList) {
                sum = sum.add(content.getPrescription_content_fee());
            }
            prescriptionToAdd.setPrescription_fee(sum);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ConstantUtils.responseFail("获取处方药品记录失败", null);
        }
        //更新状态
        prescriptionToAdd.setPrescription_execute_state(PRESCRIPTION_EXECUTE_STATE[3]); //已缴费
        //将新纪录添加
        prescriptionMapper.addPrescription(prescriptionToAdd);
        //将所有药品绑定到新创建的处方ID下
        prescriptionContentMapper.updatePrescriptionId(prescription_id,prescriptionToAdd.getPrescription_id());


        //插入新的处方发票记录
        billBefore.setBill_prescription_id(prescriptionToAdd.getPrescription_id());
        billBefore.setBill_state(BILL_STATE[0]);
        billBefore.setBill_sum(prescriptionMapper.getPrescription(prescriptionToAdd.getPrescription_id()).getPrescription_fee());
        billMapper.addBill(billBefore);

        return ConstantUtils.responseSuccess(billBefore);
    }


    public JSONObject refund(String type, Integer id) {
        if(type.equals(REFUND_TYPE[0]) || type.equals(REFUND_TYPE[1]) || type.equals(REFUND_TYPE[2])){
            System.out.println(id);
            return refundMedicalSkill(id);
        }
        else if(type.equals(REFUND_TYPE[3]) || type.equals(REFUND_TYPE[4]) ){
            return refundPrescription(id);
        }
        else {
            return responseFail("无此类型",null);
        }
    }

    public JSONObject getPrescriptions(Integer prescription_id){
            List<PrescriptionContent> prescriptionContents;
            try {
                prescriptionContents = prescriptionContentMapper.getPrescriptionContentsPositive(prescription_id);
            }catch (RuntimeException e){
                e.printStackTrace();
                return responseFail();
            }
            return responseSuccess(prescriptionContents);
    }
}
