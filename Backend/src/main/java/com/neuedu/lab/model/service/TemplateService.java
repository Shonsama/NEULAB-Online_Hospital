package com.neuedu.lab.model.service;

import com.neuedu.lab.model.mapper.TemplateMapper;
import com.neuedu.lab.model.po.Template;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TemplateService {
    @Resource
    private TemplateMapper templateMapper;

    public boolean addTemplate(Template template){
        try {
            templateMapper.addTemplate(template);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
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

    public List<Template> getTemplateByName(String template_name){
        return templateMapper.getTemplateByName(template_name);
    }

    public List<Template> getTemplateByRange(String template_range){
        return templateMapper.getTemplateByRange(template_range);
    }

    public List<Template> getTemplateByType(String template_type){
        return templateMapper.getTemplateByType(template_type);
    }
}
