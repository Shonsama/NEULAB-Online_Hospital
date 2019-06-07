package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.po.MedicalSkill;
import com.neuedu.lab.model.service.DoctorService;
import com.neuedu.lab.model.service.MedicalSkillDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ms-doctor")
public class MedicalSkillDoctorController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    MedicalSkillDoctorService medicalSkillDoctorService;

    @RequestMapping("/get-medical-skill")
    public JSONObject getMedicalSkill(@RequestBody JSONObject request){
        return medicalSkillDoctorService.getMedicalSkill(request.getString("medical_skill_execute_department"));
    }


    //项目执行 执行确认
    @RequestMapping("/confirm/medical-skill")
    public JSONObject confirmMedicalSkill(@RequestBody JSONObject request){
        return medicalSkillDoctorService.confirmMedicalSkill(request.getInteger("medical_skill_id"));
    }
    //项目执行 执行确认
    @RequestMapping("/cancel/medical-skill")
    public JSONObject cancelMedicalSkill(@RequestBody JSONObject request){
        return medicalSkillDoctorService.cancelMedicalSkill(request.getInteger("medical_skill_id"));
    }

    //添加医技项目结果
    @RequestMapping("/medical-skill/add-result")
    public JSONObject addMedicalSkillResult(@RequestBody MedicalSkill medicalSkill){
        //只用传入medical_skill_id 和 medical_skill_result
        return doctorService.addMedicalSkillResult(medicalSkill);
    }
}
