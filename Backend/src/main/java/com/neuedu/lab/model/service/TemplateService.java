package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.TemplateMapper;
import com.neuedu.lab.model.po.Template;
import com.neuedu.lab.model.po.TemplateContent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TemplateService {
    @Resource
    private TemplateMapper templateMapper;

    public JSONObject addTemplate(Template template){
        try {
            templateMapper.addTemplate(template);
        }
        catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail();
        }
        return ConstantUtils.responseSuccess(template);
    }

    public boolean deleteTemplate(Integer template_id){
        try{
            templateMapper.deleteTemplate(template_id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }return true;
    }

    public boolean updateTemplate(Template template){
        try {
            templateMapper.updateTemplate(template);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Template> getAllTemplates(Integer doctor_id,String department_id){
        return templateMapper.getAllTemplates(doctor_id,department_id);
    }

    public List<Template> getTemplatesByDoctor(Integer doctor_id){
        return templateMapper.getTemplatesByDoctor(doctor_id);
    }

    public List<Template> getTemplatesByDepartment(String department_id){
        return templateMapper.getTemplatesByDepartment(department_id);
    }


    public boolean addTemplateContentNonMedicine(TemplateContent templateContent){
        try {
            templateMapper.addTemplateContentNonMedicine(templateContent);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addTemplateContentMedicine(TemplateContent templateContent){
        try {
            templateMapper.addTemplateContentMedicine(templateContent);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteTemplateContent(Integer template_content_id){
        try{
            templateMapper.deleteTemplateContent(template_content_id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }return true;
    }

    public List<TemplateContent> getTemplateContentNonMedicine(Integer template_id){
        System.out.println("11111111");
        return templateMapper.getTemplateContentNonMedicine(template_id);
    }

    public List<TemplateContent> getTemplateContentMedicine(Integer template_id){
        return templateMapper.getTemplateContentMedicine(template_id);
    }
}
