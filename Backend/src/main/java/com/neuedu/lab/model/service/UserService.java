package com.neuedu.lab.model.service;


import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.*;
import org.hibernate.validator.cfg.ConstraintDef;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.math.BigDecimal;
import java.lang.RuntimeException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
            return ConstantUtils.responseSuccess("success", ConstantUtils.generateToken(user.getUser_id(), ConstantDefinition.USER_TYPE[0]));
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
            return ConstantUtils.responseSuccess("success", ConstantUtils.generateToken(doctor.getDoctor_id(), ConstantDefinition.USER_TYPE[1]));
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

    //日结
    @Transactional
    public List<Bill> dailyClear(Daily daily) throws RuntimeException, ParseException {
        Daily dailyRecent = dailyMapper.getDailyByUserId(daily.getDaily_user_id()); //取出最后一次日结记录
        Date end_time = dailyRecent == null ? ConstantUtils.getSystemInitializeTime() : dailyRecent.getDaily_end(); //若无记录，则按系统初始时间作为上次结束时间
        daily.setDaily_start(end_time); //将上次的结束时间作为本次的初始时间
        daily.setDaily_pass(false);  //默认审核未通过
        dailyMapper.addDaily(daily); //将记录插入到日结表
        System.out.println(daily.getDaily_start());
        System.out.println(daily.getDaily_end());
        return billMapper.getBillByUserIdAndTime(daily.getDaily_user_id(), daily.getDaily_start(), daily.getDaily_end());
    }

    //日结确认
    public boolean dailyPass(Integer daily_id) {
        try {
            dailyMapper.updateDailyPass(daily_id, true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
        bill.setBill_actual_sum(ConstantUtils.convertToNegtive(bill.getBill_actual_sum()));
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


                //更新其可退药数量
                prescriptionContentMapper.updatePrescriptionContent(prescriptionContentBefore);


                //再次获取最初的药品记录
                PrescriptionContent prescriptionContentToAdd = prescriptionContentMapper.getPrescriptionContentPositive(prescriptionContent);

                //改变数量、支付时间
                prescriptionContentToAdd.setPrescription_num(ConstantUtils.convertToNegtive(prescriptionContent.getPrescription_num()));


                //增加退药的数量为负的药品记录
                prescriptionContentMapper.addPrescriptionContent(prescriptionContentToAdd);
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

        //在处方表中新插入一条记录，并将原来的药复制绑定到新建的处方id下
        Prescription prescriptionToAdd = prescriptionMapper.getPrescription(prescription_id);
        prescriptionMapper.addPrescription(prescriptionToAdd);

        //插入发票对冲记录
        Bill billBefore;
        try {
            billBefore = billMapper.getBillByPrescriptionId(prescription_id);
            billBefore.setBill_actual_sum(ConstantUtils.convertToNegtive(billBefore.getBill_actual_sum()));
            billBefore.setBill_sum(ConstantUtils.convertToNegtive(billBefore.getBill_sum()));
            billMapper.addBill(billBefore);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ConstantUtils.responseFail("插入发票对冲记录出错", null);
        }
        //将所有药品绑定到新创建的处方ID下
        List<PrescriptionContent> contentList;
        BigDecimal sum = new BigDecimal(0);
        try {
            contentList = prescriptionContentMapper.getPrescriptionContentsNew(prescription_id);
            for (PrescriptionContent content : contentList) {
                content.setPrescription_id(prescriptionToAdd.getPrescription_id());
                content.setPrescription_content_fee(content.getPrescription_unit_price().multiply(new BigDecimal(content.getPrescription_num())));
                sum = sum.add(content.getPrescription_content_fee());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ConstantUtils.responseFail("获取处方药品记录失败", null);
        }

        //更改新发票的金额
        prescriptionToAdd.setPrescription_fee(sum);
        prescriptionMapper.updatePrescriptionFee(prescriptionToAdd);

        //插入新的处方发票记录
        billBefore.setBill_prescription_id(prescriptionToAdd.getPrescription_id());
        billBefore.setBill_actual_sum(prescriptionToAdd.getPrescription_fee());
        billBefore.setBill_sum(prescriptionToAdd.getPrescription_fee());
        billMapper.addBill(billBefore);

        return ConstantUtils.responseSuccess(billBefore);
    }



}
