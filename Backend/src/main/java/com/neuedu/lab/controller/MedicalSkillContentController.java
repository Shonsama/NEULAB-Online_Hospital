package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.MedicalSkillContent;
import com.neuedu.lab.model.service.MedicalSkillContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical-skill-content")
public class MedicalSkillContentController {
    @Autowired
    private MedicalSkillContentService medicalSkillContentService;

    @RequestMapping("/get-all")
//    显示所有科室信息
    public JSONObject getAllMedicalSkillContents(){
        return ConstantUtils.responseSuccess(medicalSkillContentService.getAllMedicalSkillContents());
    }

    @RequestMapping("/get")
//    显示查询科室信息
    public JSONObject getMedicalSkillContent(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(medicalSkillContentService.getMedicalSkillContent(request.getString("medical_skill_content_id")));
    }

    @RequestMapping("/add")
    public JSONObject addMedicalSkillContent(@RequestBody MedicalSkillContent medicalSkillContent){
        if(medicalSkillContentService.addMedicalSkillContent(medicalSkillContent)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update")
    public JSONObject updateMedicalSkillContent(@RequestBody MedicalSkillContent medicalSkillContent){
        if(medicalSkillContentService.updateMedicalSkillContent(medicalSkillContent)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/delete")
    public JSONObject deleteMedicalSkillContent(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(medicalSkillContentService.deleteMedicalSkillContent(request.getString("medical_skill_content_id")));
    }
}
