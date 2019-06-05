package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Template;

import java.util.List;

public interface TemplateMapper {
    public void addTemplate(Template template);
    public void deleteTemplate(Integer template_id);
    public void updateTemplate(Template template);

    List<Template> getTemplateByName(String template_name);
    List<Template> getTemplateByRange(String template_range);
    List<Template> getTemplateByType(String tamplate_type);
}
