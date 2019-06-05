package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.Template;
import com.neuedu.lab.model.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @RequestMapping("/get-by-name")
    public JSONObject getTemplateByName(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(templateService.getTemplateByName(request.getString("template_name")));
    }

    @RequestMapping("/get-by-range")
    public JSONObject getTemplateByRange(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(templateService.getTemplateByRange(request.getString("template_range")));
    }

    @RequestMapping("/get-by-type")
    public JSONObject getTemplateByType(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(templateService.getTemplateByType(request.getString("template_type")));
    }

    @RequestMapping("/add")
    public JSONObject addTemplate(@RequestBody Template template){
        if(templateService.addTemplate(template)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/delete")
    public JSONObject deleteTemplate(@RequestBody JSONObject request){
        if(templateService.deleteTemplate(request.getInteger("template_id"))){
            return ConstantUtils.responseSuccess(null);
        }else{
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update")
    public JSONObject updateTemplate(@RequestBody Template template){
        if(templateService.updateTemplate(template)){
            return ConstantUtils.responseSuccess(null);
        }else{
            return ConstantUtils.responseFail(null);
        }
    }
}
