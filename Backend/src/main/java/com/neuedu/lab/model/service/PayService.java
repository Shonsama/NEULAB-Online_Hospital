package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.utils.ConstantDefinition;
import com.neuedu.lab.utils.ConstantUtils;
import com.neuedu.lab.model.mapper.BillMapper;
import com.neuedu.lab.model.mapper.MedicalSkillMapper;
import com.neuedu.lab.model.mapper.PrescriptionMapper;
import com.neuedu.lab.model.po.Bill;
import com.neuedu.lab.model.po.MedicalSkill;
import com.neuedu.lab.model.po.Prescription;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PayService {
    @Resource
    private MedicalSkillMapper medicalSkillMapper;

    @Resource
    private PrescriptionMapper prescriptionMapper;

    @Resource
    private BillMapper billMapper;

    //收费
/*    @Transactional
    public JSONObject payMedicalSkillFee(List<Integer> medical_skill_ids) {
        Integer currentPayable = 0;
        for (Integer medical_skill_id : medical_skill_ids) {
            if (medicalSkillMapper.getMedicalSkill(medical_skill_id).getMedical_skill_execute_state().equals(ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[1])) {
                currentPayable++;
            }
        }
        if (currentPayable == medical_skill_ids.size()) {
            for (Integer medical_skill_id : medical_skill_ids) {
                medicalSkillMapper.updateMedicalSkillState(medical_skill_id, ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[3], null);
            }
            return ConstantUtils.responseSuccess("缴费成功");
        } else {
            return ConstantUtils.responseFail("当前医技状态不允许缴费");
        }
    }*/
    @Transactional
    public JSONObject payFee(Integer id, String type, Integer register_id, Integer user_id) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Bill bill = new Bill();
        bill.setBill_state(ConstantDefinition.BILL_STATE[0]);
        bill.setBill_register_id(register_id);
        bill.setBill_user_id(user_id);
        if (type.equals("检查") || type.equals("检验") || type.equals("处置")) {//医技
            if (medicalSkillMapper.getMedicalSkill(id).getMedical_skill_execute_state().equals(ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[1])) {
                //更改medicalSkill缴费状态
                medicalSkillMapper.updateMedicalSkillState(id, ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[3], null);
                medicalSkillMapper.updateMedicalSkillPaytime(id,new Date(),user_id);
                //增加发票
                bill.setBill_type(medicalSkillMapper.getMedicalSkill(id).getMedical_skill_type());
                bill.setBill_fee_cat_name(medicalSkillMapper.getMedicalSkill(id).getMedical_skill_type());
                bill.setBill_sum(medicalSkillMapper.getMedicalSkill(id).getMedical_skill_fee());
                bill.setBill_medical_skill_id(id);
                billMapper.addBill(bill);
                String billNum = sdf.format(c.getTime()).replaceAll("[[\\s-:punct:]]","") + String.format("%03d", bill.getBill_id());
                billMapper.updateBillNum(billNum,bill.getBill_id());
                //返回成功
                return ConstantUtils.responseSuccess("医技项目缴费成功",billMapper.getBillById(bill.getBill_id()));
            } else {
                return ConstantUtils.responseFail("该医技项目不允许缴费");
            }
        } else if (type.equals("西药") || type.equals("中药")) {//处方
            if (prescriptionMapper.getPrescription(id).getPrescription_execute_state().equals(ConstantDefinition.PRESCRIPTION_EXECUTE_STATE[1])) {
                prescriptionMapper.updatePrescriptionState(id, ConstantDefinition.PRESCRIPTION_EXECUTE_STATE[3]);
                prescriptionMapper.updatePrescriptionPaytime(id,new Date(),user_id);

                //增加发票
                bill.setBill_type(prescriptionMapper.getPrescription(id).getPrescription_type());
                bill.setBill_fee_cat_name(prescriptionMapper.getPrescription(id).getPrescription_type());
                bill.setBill_sum(prescriptionMapper.getPrescription(id).getPrescription_fee());
                bill.setBill_prescription_id(id);
                billMapper.addBill(bill);
                String billNum = sdf.format(c.getTime()).replaceAll("[[\\s-:punct:]]","") + String.format("%03d", bill.getBill_id());
                billMapper.updateBillNum(billNum,bill.getBill_id());
                return ConstantUtils.responseSuccess("处方项目缴费成功",billMapper.getBillById(bill.getBill_id()));
            } else {
                return ConstantUtils.responseFail("该处方项目不允许缴费");
            }
        } else {
            return ConstantUtils.responseFail("type无效!");
        }
    }

    /*@Transactional
    public JSONObject payPrescriptionFee(List<Integer> prescription_ids) {
        Integer currentPayable = 0;
        for (Integer prescription_id : prescription_ids) {
            if (prescriptionMapper.getPrescription(prescription_id).getPrescription_execute_state().equals(ConstantDefinition.PRESCRIPTION_EXECUTE_STATE[1])) {
                currentPayable++;
            }
        }
        if (currentPayable == prescription_ids.size()) {
            for (Integer prescription_id : prescription_ids) {
                prescriptionMapper.updatePrescriptionState(prescription_id, ConstantDefinition.PRESCRIPTION_EXECUTE_STATE[3]);
            }
            return ConstantUtils.responseSuccess("缴费成功");
        } else {
            return ConstantUtils.responseFail("当前处方状态不允许缴费");
        }
    }*/

    //获取当前病人医技已缴费对象
    public List<MedicalSkill> getMedicalSkillsInCanceledOrPaid(Integer register_info_patient_id) {
        return medicalSkillMapper.getMedicalSkillsInCanceledOrPaid(register_info_patient_id);
    }

    //获取当前病人医技未缴费对象
    public List<MedicalSkill> getMedicalSkillsInDrew(Integer register_info_patient_id) {
        return medicalSkillMapper.getMedicalSkillsInDrew(register_info_patient_id);
    }

    //获取当前病人处方已缴费对象
    public List<Prescription> getPrescriptionsInReturnedOrPaid(Integer register_info_patient_id) {
        return prescriptionMapper.getPrescriptionsInReturnedOrPaid(register_info_patient_id);
    }

    //获取当前病人处方未缴费对象
    public List<Prescription> getPrescriptionsInSent(Integer register_info_patient_id) {
        return prescriptionMapper.getPrescriptionsInSent(register_info_patient_id);
    }
}
