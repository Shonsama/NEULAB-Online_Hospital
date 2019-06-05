package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.DiseaseInfo;
import com.neuedu.lab.model.service.DiseaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diseaseInfo")
public class DiseaseInfoController {
    @Autowired
    private DiseaseInfoService diseaseInfoService;

    @RequestMapping("/get-all")
//    显示所有疾病信息
    public JSONObject getAllDiseases(){
        return ConstantUtils.responseSuccess(diseaseInfoService.getAllDiseases());
    }

    @RequestMapping("/get")
//    显示查询疾病信息
    public JSONObject getDisease(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(diseaseInfoService.getDisease(request.getString("disease_id")));
    }

    @RequestMapping("/add")
    public JSONObject addDisease(@RequestBody DiseaseInfo diseaseInfo){
        if(diseaseInfoService.addDisease(diseaseInfo)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update")
    public JSONObject updateConstant(@RequestBody DiseaseInfo diseaseInfo){
        if(diseaseInfoService.updateDisease(diseaseInfo)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/delete")
    public JSONObject deleteConstant(@RequestBody JSONObject disease_id){
        String getString = disease_id.getString("disease_id");
        if(diseaseInfoService.deleteDisease(getString)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }
}
