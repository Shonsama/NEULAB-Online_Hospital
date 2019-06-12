package com.neuedu.lab.model.po;

public class Doctor {
    private String doctor_position;
    private boolean doctor_arrange_or_not;
    private String doctor_account;
    private String doctor_password;
    private String doctor_type;
    private String doctor_name;
    private Integer doctor_register_level_id;
    private Integer doctor_id;
    private String doctor_department_id;
    private Integer doctor_register_num;

    public Integer getDoctor_register_num() {
        return doctor_register_num;
    }

    public void setDoctor_register_num(Integer doctor_register_num) {
        this.doctor_register_num = doctor_register_num;
    }

    public String getDoctor_position() {
        return doctor_position;
    }

    public void setDoctor_position(String doctor_position) {
        this.doctor_position = doctor_position;
    }

    public boolean isDoctor_arrange_or_not() {
        return doctor_arrange_or_not;
    }

    public void setDoctor_arrange_or_not(boolean doctor_arrange_or_not) {
        this.doctor_arrange_or_not = doctor_arrange_or_not;
    }

    public String getDoctor_account() {
        return doctor_account;
    }

    public void setDoctor_account(String doctor_account) {
        this.doctor_account = doctor_account;
    }

    public String getDoctor_password() {
        return doctor_password;
    }

    public void setDoctor_password(String doctor_password) {
        this.doctor_password = doctor_password;
    }

    public String getDoctor_type() {
        return doctor_type;
    }

    public void setDoctor_type(String doctor_type) {
        this.doctor_type = doctor_type;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public Integer getDoctor_register_level_id() {
        return doctor_register_level_id;
    }

    public void setDoctor_register_level_id(Integer doctor_register_level_id) {
        this.doctor_register_level_id = doctor_register_level_id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_department_id() {
        return doctor_department_id;
    }

    public void setDoctor_department_id(String doctor_department_id) {
        this.doctor_department_id = doctor_department_id;
    }
}
