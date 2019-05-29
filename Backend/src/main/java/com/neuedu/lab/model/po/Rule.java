package com.neuedu.lab.model.po;

public class Rule {
    private Integer rule_id;
    private String rule_name;
    private String rule_work_time;
    private Integer rule_doctor_id;

    public Integer getRule_id() {
        return rule_id;
    }

    public void setRule_id(Integer rule_id) {
        this.rule_id = rule_id;
    }

    public String getRule_name() {
        return rule_name;
    }

    public void setRule_name(String rule_name) {
        this.rule_name = rule_name;
    }

    public String getRule_work_time() {
        return rule_work_time;
    }

    public void setRule_work_time(String rule_work_time) {
        this.rule_work_time = rule_work_time;
    }

    public Integer getRule_doctor_id() {
        return rule_doctor_id;
    }

    public void setRule_doctor_id(Integer rule_doctor_id) {
        this.rule_doctor_id = rule_doctor_id;
    }
}
