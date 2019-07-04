package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.mapper.MedicalSkillContentMapper;
import com.neuedu.lab.model.mapper.MedicalSkillMapper;
import com.neuedu.lab.model.mapper.PatientMapper;
import com.neuedu.lab.model.mapper.RegisterMapper;
import com.neuedu.lab.model.po.MedicalSkill;
import com.neuedu.lab.model.po.Patient;
import com.neuedu.lab.utils.ConstantDefinition;
import com.neuedu.lab.utils.ConstantUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.neuedu.lab.utils.ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE;
import static com.neuedu.lab.utils.ConstantUtils.responseFail;
import static com.neuedu.lab.utils.ConstantUtils.responseSuccess;

@Service
public class MedicalSkillDoctorService {
    @Resource
    MedicalSkillMapper medicalSkillMapper;
    @Resource
    RegisterMapper registerMapper;
    @Resource
    PatientMapper patientMapper;
    @Resource
    MedicalSkillContentMapper medicalSkillContentMapper;

    public JSONObject confirmMedicalSkill(Integer medical_skill_id,Integer medical_skill_execute_doctor_id){
        medicalSkillMapper.updateMedicalSkillState(medical_skill_id,
                ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[6],medical_skill_execute_doctor_id);
        MedicalSkill result = medicalSkillMapper.getMedicalSkill(medical_skill_id);
        result.setMedicalSkillContent(medicalSkillContentMapper.getMedicalSkillContent(result.getMedical_skill_content_id()));
        return ConstantUtils.responseSuccess(result);
    }
    public JSONObject cancelMedicalSkill(Integer medical_skill_id, Integer medical_skill_execute_doctor_id){
        medicalSkillMapper.updateMedicalSkillState(medical_skill_id,
                ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[7], medical_skill_execute_doctor_id);
        MedicalSkill result = medicalSkillMapper.getMedicalSkill(medical_skill_id);
        result.setMedicalSkillContent(medicalSkillContentMapper.getMedicalSkillContent(result.getMedical_skill_content_id()));
        return ConstantUtils.responseSuccess(result);
    }

    public JSONObject getMedicalSkill(String medical_skill_execute_department){
        List<MedicalSkill> medicalSkills;
        try{
            medicalSkills = medicalSkillMapper.getMedicalSkillByDepartmentId(medical_skill_execute_department,ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[3]);
            for(MedicalSkill medicalSkill : medicalSkills){
                medicalSkill.setRegister(registerMapper.getRegister(medicalSkill.getMedical_skill_register_info_id()));
                medicalSkill.getRegister().setPatient(patientMapper.getPatientByRecordId(medicalSkill.getRegister().getRegister_info_patient_id()));
                medicalSkill.setMedicalSkillContent(medicalSkillContentMapper.getMedicalSkillContent(medicalSkill.getMedical_skill_content_id()));
            }

        }catch (RuntimeException e){
            e.printStackTrace();
            return ConstantUtils.responseFail();
        }
        return ConstantUtils.responseSuccess(medicalSkills);

    }

    public JSONObject getMedicalSkillByPatient(String medical_skill_execute_department, Integer patient_id){

        List<MedicalSkill> medicalSkills;
        try{
            medicalSkills = medicalSkillMapper.getDepartmentMedicalSkillByPatientId(patient_id,MEDICAL_SKILL_EXECUTE_STATE[3],
                    MEDICAL_SKILL_EXECUTE_STATE[6],medical_skill_execute_department);
            for(MedicalSkill medicalSkill:medicalSkills){
                medicalSkill.setMedicalSkillContent(
                        medicalSkillContentMapper.getMedicalSkillContent(medicalSkill.getMedical_skill_content_id()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail(e);
        }
        return responseSuccess(medicalSkills);
        }

    public JSONObject addMedicalSkillResult(MedicalSkill medicalSkill){
        MedicalSkill medicalSkillBefore;
        try{
            medicalSkillBefore= medicalSkillMapper.getMedicalSkill(medicalSkill.getMedical_skill_id());
        }
        catch (RuntimeException e){
            e.printStackTrace();
            return responseFail("获取医技项目失败",null);
        }
        if(medicalSkillBefore == null){
            return responseFail("无此医技项目",null);
        }

        medicalSkillBefore.setMedical_skill_execute_state(MEDICAL_SKILL_EXECUTE_STATE[4]);
        medicalSkillBefore.setMedical_skill_result(medicalSkill.getMedical_skill_result());

        try{
            medicalSkillMapper.updateMedicalSkillResult(medicalSkillBefore);
        }catch (RuntimeException e){
            return responseFail("结果添加失败",null);
        }
        medicalSkillBefore.setMedicalSkillContent(medicalSkillContentMapper.getMedicalSkillContent(medicalSkillBefore.getMedical_skill_content_id()));


        return responseSuccess(medicalSkillBefore);

    }

    public JSONObject getPatients(String medical_skill_execute_department,String medical_skill_type) {
        List<Patient> patients;
        try{
           patients = medicalSkillMapper.getAllPatients(medical_skill_execute_department,MEDICAL_SKILL_EXECUTE_STATE[3],medical_skill_type);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail(e);
        }
        return responseSuccess(patients);
    }
}
