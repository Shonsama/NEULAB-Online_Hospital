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
import java.util.ArrayList;
import java.util.List;

import static com.neuedu.lab.Utils.ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE;
import static com.neuedu.lab.Utils.ConstantUtils.responseFail;
import static com.neuedu.lab.Utils.ConstantUtils.responseSuccess;

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
            medicalSkills = medicalSkillMapper.getMedicalSkillByDepartmentId(medical_skill_execute_department,ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[3]);
            for(MedicalSkill medicalSkill : medicalSkills){
                medicalSkill.setRegister(registerMapper.getRegister(medicalSkill.getMedical_skill_register_info_id()));
                medicalSkill.getRegister().setPatient(patientMapper.getPatientByRecordId(medicalSkill.getRegister().getRegister_info_patient_id()));
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
            medicalSkills = medicalSkillMapper.getDepartmentMedicalSkillByPatientId(patient_id,MEDICAL_SKILL_EXECUTE_STATE[3],medical_skill_execute_department);
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

        return responseSuccess(medicalSkillBefore);

    }

}
