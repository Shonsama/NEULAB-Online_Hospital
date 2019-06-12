package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.MedicalSkill;
import com.neuedu.lab.model.po.Patient;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MedicalSkillMapper {

    public void addMedicalSkill(MedicalSkill medicalSkill);

    public void deleteMedicalSkill(Integer medical_skill_id);

    void updateMedicalSkillState(@Param("medical_skill_id") Integer medical_skill_id,
                                 @Param("medical_skill_execute_state") String medical_skill_execute_state,
                                 @Param("medical_skill_execute_doctor_id") Integer medical_skill_execute_doctor_id);

    void updateMedicalSkillPaytime (@Param("medical_skill_id") Integer medical_skill_id,
                                 @Param("medical_skill_pay_time") Date medical_skill_pay_time,
                                 @Param("medical_skill_user_id") Integer medical_skill_user_id);

    MedicalSkill getMedicalSkill(Integer medical_skill_id);

    //用于添加医技结果
    void updateMedicalSkillResult(MedicalSkill medicalSkill);

    //用于医技项目缴费
    List<MedicalSkill> getMedicalSkillByRegisterId(Integer medical_skill_register_info_id, String medical_skill_execute_state);

    //用于医技部门查找所有记录患者
    List<MedicalSkill> getMedicalSkillByDepartmentId(@Param("medical_skill_execute_department") String medical_skill_execute_department, @Param("medical_skill_execute_state") String medical_skill_execute_state);

    //用于医生查看病人病历
    List<MedicalSkill> getMedicalSkillByRegisterIdForDoctor(Integer medical_skill_register_info_id);

    //用于查看一个病人的所有已缴费记录
    List<MedicalSkill> getDepartmentMedicalSkillByPatientId(@Param("register_info_patient_id") Integer register_info_patient_id,
                                                            @Param("medical_skill_execute_state_1") String medical_skill_execute_state_1,
                                                            @Param("medical_skill_execute_state_2") String medical_skill_execute_state_2,
                                                            @Param("medical_skill_execute_department") String medical_skill_execute_department);


    //用于医技部门查找所有待诊患者
    List<Patient> getAllPatients(@Param("medical_skill_execute_department") String medical_skill_execute_department,
                                 @Param("medical_skill_execute_state") String medical_skill_execute_state);


    //author: LEE
    List<MedicalSkill> getMedicalSkillsInCanceledOrPaid(Integer register_info_patient_id);

    List<MedicalSkill> getMedicalSkillsInDrew(Integer register_info_patient_id);

}
