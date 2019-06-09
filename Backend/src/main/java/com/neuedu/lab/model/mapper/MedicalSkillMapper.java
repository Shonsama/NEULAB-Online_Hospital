package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.MedicalSkill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MedicalSkillMapper {

    public void addMedicalSkill(MedicalSkill medicalSkill);

    public void deleteMedicalSkill(Integer medical_skill_id);

    void updateMedicalSkillState(@Param("medical_skill_id") Integer medical_skill_id,@Param("medical_skill_execute_state") String medical_skill_execute_state);

    MedicalSkill getMedicalSkill(Integer medical_skill_id);

    //用于添加医技结果
    void updateMedicalSkillResult(MedicalSkill medicalSkill);

    //用于医技项目缴费
    List<MedicalSkill> getMedicalSkillByRegisterId(Integer medical_skill_register_info_id,String medical_skill_execute_state);

    //用于医技部门查找所有记录患者
    List<MedicalSkill> getMedicalSkillByDepartmentId(String medical_skill_execute_department, String medical_skill_execute_state);

    //用于医生查看病人病历
    List<MedicalSkill> getMedicalSkillByRegisterIdForDoctor(Integer medical_skill_register_info_id);
}
