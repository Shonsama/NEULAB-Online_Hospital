package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.MedicalSkill;

import java.util.List;

public interface MedicalSkillMapper {

    public void addMedicalSkill(MedicalSkill medicalSkill);

    public void deleteMedicalSkill(Integer medical_skill_id);

    void updateMedicalSkillState(Integer medical_skill_id,String medical_skill_execute_state);

    MedicalSkill getMedicalSkill(Integer medical_skill_id);

    //用于添加医技结果
    void updateMedicalSkillResult(MedicalSkill medicalSkill);

    //用于医技项目缴费
    List<MedicalSkill> getMedicalSkillByRegisterId(Integer medical_skill_register_info_id,String medical_skill_execute_state);

}
