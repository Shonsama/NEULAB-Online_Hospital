package com.neuedu.lab.model.po;

public class User {
    private Integer user_id;
    private String user_password;
    private String user_account;
    private String user_type;
    private String user_name;
    private String user_department_id;

    public String getUser_department_id() {
        return user_department_id;
    }

    public void setUser_department_id(String user_department_id) {
        this.user_department_id = user_department_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
