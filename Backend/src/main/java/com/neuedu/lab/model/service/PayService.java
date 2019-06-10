package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.MedicalSkillMapper;
import com.neuedu.lab.model.mapper.PrescriptionMapper;
import com.neuedu.lab.model.po.MedicalSkill;
import com.neuedu.lab.model.po.Prescription;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PayService {
    @Resource
    private MedicalSkillMapper medicalSkillMapper;

    @Resource
    private PrescriptionMapper prescriptionMapper;

    //收费
    //医技项目收费
    @Transactional
    public JSONObject payMedicalSkillFee(List<Integer> medical_skill_ids) {
        Integer currentPayable = 0;
        for (Integer medical_skill_id : medical_skill_ids) {
            System.out.println("&");
            if (medicalSkillMapper.getMedicalSkill(medical_skill_id).getMedical_skill_execute_state().equals(ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[1])) {
                currentPayable++;
            }
        }
        if (currentPayable == medical_skill_ids.size()) {
          for (Integer medical_skill_id : medical_skill_ids) {
//            for (int medical_skill_id = 0; medical_skill_id < medical_skill_ids.size(); medical_skill_id++) {
                medicalSkillMapper.updateMedicalSkillState(medical_skill_id, ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[3],null);
            }
            return ConstantUtils.responseSuccess("缴费成功");
        } else {
            return ConstantUtils.responseFail("当前医技状态不允许缴费");
        }
    }

    @Transactional
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
    }

    //获取当前病人医技已缴费对象
    public List<MedicalSkill> getMedicalSkillsInCanceledOrPaid(Integer register_info_patient_id){
        return medicalSkillMapper.getMedicalSkillsInCanceledOrPaid(register_info_patient_id);
    }

    //获取当前病人医技未缴费对象
    public List<MedicalSkill> getMedicalSkillsInDrew(Integer register_info_patient_id){
        return medicalSkillMapper.getMedicalSkillsInDrew(register_info_patient_id);
    }

    //获取当前病人处方已缴费对象
    public List<Prescription> getPrescriptionsInReturnedOrPaid(Integer register_info_patient_id){
        return prescriptionMapper.getPrescriptionsInReturnedOrPaid(register_info_patient_id);
    }

    //获取当前病人处方未缴费对象
    public List<Prescription> getPrescriptionsInSent(Integer register_info_patient_id){
        return prescriptionMapper.getPrescriptionsInSent(register_info_patient_id);
    }
}
