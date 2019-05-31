package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.po.DiseaseInfo;
import com.neuedu.lab.model.service.DiseaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/diseaseinfo")
public class DiseaseInfoController {
    @Autowired
    private DiseaseInfoService diseaseInfoService;

    @RequestMapping("/getall")
//    显示所有疾病信息
    public List<DiseaseInfo> getAllDiseases(){
        return diseaseInfoService.getAllDiseases();
    }

    @RequestMapping("/get")
//    显示查询疾病信息
    public List<DiseaseInfo> getDisease(@RequestBody JSONObject disease_id){
        String getString = disease_id.getString("disease_id");
        return diseaseInfoService.getDisease(getString);
    }

    @RequestMapping("/add")
    public String addDisease(@RequestBody DiseaseInfo diseaseInfo){
        if(diseaseInfoService.addDisease(diseaseInfo)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/update")
    public String updateConstant(@RequestBody DiseaseInfo diseaseInfo){
        if(diseaseInfoService.updateDisease(diseaseInfo)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/delete")
    public String deleteConstant(@RequestBody DiseaseInfo diseaseInfo){
        if(diseaseInfoService.deleteDisease(diseaseInfo)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }
}
