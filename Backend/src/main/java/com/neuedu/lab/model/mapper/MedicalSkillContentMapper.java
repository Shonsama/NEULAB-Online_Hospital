package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.MedicalSkillContent;

import java.util.List;

public interface MedicalSkillContentMapper {
    public List<MedicalSkillContent> getAllMedicalSkillContents();
    public void addMedicalSkillContent(MedicalSkillContent medicalSkillContent);
    public void deleteMedicalSkillContent(String medical_skill_content_id);
    public void updateMedicalSkillContent(MedicalSkillContent medicalSkillContent);
    public MedicalSkillContent getMedicalSkillContent(String medical_skill_content_id);
}
