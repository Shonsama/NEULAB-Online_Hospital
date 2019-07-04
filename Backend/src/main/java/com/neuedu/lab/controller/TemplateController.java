package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.utils.ConstantUtils;
import com.neuedu.lab.model.po.Template;
import com.neuedu.lab.model.po.TemplateContent;
import com.neuedu.lab.model.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模板接口，将来整合到医生接口控制类
 */
@RestController
@RequestMapping("/template")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    //    manage template
    @RequestMapping("/get-all")
    public JSONObject getAllTemplates(@RequestBody JSONObject request) {
        System.out.println(request.getInteger("doctor_id") + request.getString("department_id"));
        return ConstantUtils.responseSuccess(templateService.getAllTemplates(request.getInteger("doctor_id"), request.getString("department_id")));
    }

    @RequestMapping("/get-by-doctor")
    public JSONObject getTemplatesByDoctor(@RequestBody JSONObject request) {
        return ConstantUtils.responseSuccess(templateService.getTemplatesByDoctor(request.getInteger("doctor_id")));
    }

    @RequestMapping("/get-by-department")
    public JSONObject getTemplatesByDepartment(@RequestBody JSONObject request) {
        return ConstantUtils.responseSuccess(templateService.getTemplatesByDepartment(request.getString("department_id")));
    }

    @RequestMapping("/add-template")
    public JSONObject addTemplate(@RequestBody Template template) {
        return templateService.addTemplate(template);

    }

    @RequestMapping("/delete-template")
    public JSONObject deleteTemplate(@RequestBody JSONObject request) {
        if (templateService.deleteTemplate(request.getInteger("template_id"))) {
            return ConstantUtils.responseSuccess(null);
        } else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update-template")
    public JSONObject updateTemplate(@RequestBody Template template) {
        if (templateService.updateTemplate(template)) {
            return ConstantUtils.responseSuccess(null);
        } else {
            return ConstantUtils.responseFail(null);
        }
    }


    //    manage content
    @RequestMapping("/add-content-non-medicine")
    public JSONObject addTemplateContentNonMedicine(@RequestBody TemplateContent templateContent) {
        if (templateService.addTemplateContentNonMedicine(templateContent)) {
            return ConstantUtils.responseSuccess(null);
        } else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/add-content-medicine")
    public JSONObject addTemplateContentMedicine(@RequestBody TemplateContent templateContent) {
        if (templateService.addTemplateContentMedicine(templateContent)) {
            return ConstantUtils.responseSuccess(null);
        } else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/delete-content")
    public JSONObject deleteTemplateContent(@RequestBody JSONObject request) {
        if (templateService.deleteTemplateContent(request.getInteger("template_content_id"))) {
            return ConstantUtils.responseSuccess(null);
        } else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/get-content-non-medicine")
    public JSONObject getTemplateContentNonMedicine(@RequestBody JSONObject request) {
        return ConstantUtils.responseSuccess(templateService.getTemplateContentNonMedicine(request.getInteger("template_id")));
    }

    @RequestMapping("/get-content-medicine")
    public JSONObject getTemplateContentMedicine(@RequestBody JSONObject request) {
        return ConstantUtils.responseSuccess(templateService.getTemplateContentMedicine(request.getInteger("template_id")));
    }
}
