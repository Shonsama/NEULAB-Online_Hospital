package com.neuedu.lab.model.po;

public class PatientUser {
    private Integer patient_user_id;
    private String patient_account;
    private String patient_password;
    private Integer patient_connect_id;

    public Integer getPatient_id() {
        return patient_user_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_user_id = patient_id;
    }

    public String getPatient_account() {
        return patient_account;
    }

    public void setPatient_account(String patient_account) {
        this.patient_account = patient_account;
    }

    public String getPatient_password() {
        return patient_password;
    }

    public void setPatient_password(String patient_password) {
        this.patient_password = patient_password;
    }

    public Integer getPatient_connect_id() {
        return patient_connect_id;
    }

    public void setPatient_connect_id(Integer patient_connect_id) {
        this.patient_connect_id = patient_connect_id;
    }
}
