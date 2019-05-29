package com.neuedu.lab.model.po;
import java.util.Date;

public class Template {
    private Integer template_id;
    private String template_type;
    private String template_name;
    private String template_range;
    private Date template_init_date;
    private Integer template_doctor_id;
    private String template_non_medicine_id;
    private String template_medicine_id;

    public Integer getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(Integer template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_type() {
        return template_type;
    }

    public void setTemplate_type(String template_type) {
        this.template_type = template_type;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getTemplate_range() {
        return template_range;
    }

    public void setTemplate_range(String template_range) {
        this.template_range = template_range;
    }

    public Date getTemplate_init_date() {
        return template_init_date;
    }

    public void setTemplate_init_date(Date template_init_date) {
        this.template_init_date = template_init_date;
    }

    public Integer getTemplate_doctor_id() {
        return template_doctor_id;
    }

    public void setTemplate_doctor_id(Integer template_doctor_id) {
        this.template_doctor_id = template_doctor_id;
    }

    public String getTemplate_non_medicine_id() {
        return template_non_medicine_id;
    }

    public void setTemplate_non_medicine_id(String template_non_medicine_id) {
        this.template_non_medicine_id = template_non_medicine_id;
    }

    public String getTemplate_medicine_id() {
        return template_medicine_id;
    }

    public void setTemplate_medicine_id(String template_medicine_id) {
        this.template_medicine_id = template_medicine_id;
    }
}
