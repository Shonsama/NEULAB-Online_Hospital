package com.neuedu.lab.model.service;

import com.neuedu.lab.model.mapper.MedicalSkillContentMapper;
import com.neuedu.lab.model.po.MedicalSkillContent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MedicalSkillContentService {
    @Resource
    private MedicalSkillContentMapper medicalSkillContentMapper;

    public List<MedicalSkillContent> getAllMedicalSkillContents(){
        return medicalSkillContentMapper.getAllMedicalSkillContents();
    }

    public MedicalSkillContent getMedicalSkillContent(String medical_skill_content_id){
        return medicalSkillContentMapper.getMedicalSkillContent(medical_skill_content_id);
    }

    public boolean addMedicalSkillContent(MedicalSkillContent medicalSkillContent){
        try {
            medicalSkillContentMapper.addMedicalSkillContent(medicalSkillContent);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteMedicalSkillContent(String medical_skill_content_id){
        try {
            medicalSkillContentMapper.deleteMedicalSkillContent(medical_skill_content_id);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateMedicalSkillContent(MedicalSkillContent medicalSkillContent){
        try {
            medicalSkillContentMapper.updateMedicalSkillContent(medicalSkillContent);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
