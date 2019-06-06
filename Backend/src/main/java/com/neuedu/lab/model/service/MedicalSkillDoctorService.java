package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.MedicalSkillMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MedicalSkillDoctorService {
    @Resource
    MedicalSkillMapper medicalSkillMapper;

    public JSONObject confirmMedicalSkill(Integer medical_skill_id){
        medicalSkillMapper.updateMedicalSkillState(medical_skill_id, ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[6]);
        return ConstantUtils.responseSuccess(medicalSkillMapper.getMedicalSkill(medical_skill_id));
    }
    public JSONObject cancelMedicalSkill(Integer medical_skill_id){
        medicalSkillMapper.updateMedicalSkillState(medical_skill_id, ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[7]);
        return ConstantUtils.responseSuccess(medicalSkillMapper.getMedicalSkill(medical_skill_id));
    }
}
