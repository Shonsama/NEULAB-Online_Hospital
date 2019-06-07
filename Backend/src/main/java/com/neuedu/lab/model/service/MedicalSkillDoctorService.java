package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.MedicalSkillMapper;
import com.neuedu.lab.model.mapper.PatientMapper;
import com.neuedu.lab.model.mapper.RegisterMapper;
import com.neuedu.lab.model.po.MedicalSkill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MedicalSkillDoctorService {
    @Resource
    MedicalSkillMapper medicalSkillMapper;
    @Resource
    RegisterMapper registerMapper;
    @Resource
    PatientMapper patientMapper;

    public JSONObject confirmMedicalSkill(Integer medical_skill_id){
        medicalSkillMapper.updateMedicalSkillState(medical_skill_id, ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[6]);
        return ConstantUtils.responseSuccess(medicalSkillMapper.getMedicalSkill(medical_skill_id));
    }
    public JSONObject cancelMedicalSkill(Integer medical_skill_id){
        medicalSkillMapper.updateMedicalSkillState(medical_skill_id, ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[7]);
        return ConstantUtils.responseSuccess(medicalSkillMapper.getMedicalSkill(medical_skill_id));
    }

    public JSONObject getMedicalSkill(String medical_skill_execute_department){
        List<MedicalSkill> medicalSkills;
        try{
            medicalSkills = medicalSkillMapper.getMedicalSkillByDepartmentId(medical_skill_execute_department);
            for(MedicalSkill medicalSkill : medicalSkills){
                medicalSkill.setRegister(registerMapper.getRegister(medicalSkill.getMedical_skill_register_info_id()));
                medicalSkill.getRegister().setPatient(patientMapper.getPatientByRecordId(medicalSkill.getRegister().getRegister_info_patient_id()));
            }

        }catch (RuntimeException e){
            e.printStackTrace();
            return ConstantUtils.responseFail(null);
        }
        return ConstantUtils.responseSuccess(medicalSkills);

    }
}
