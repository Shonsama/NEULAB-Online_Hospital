package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.MedicalSkill;

public interface MedicalSkillMapper {

    public void addMedicalSkill(MedicalSkill medicalSkill);

    public void deleteMedicalSkill(Integer medical_skill_id);

    void updateMedicalSkillState(Integer medical_skill_id,String medical_skill_execute_state);

    MedicalSkill getMedicalSkill(Integer medical_skill_id);
}
