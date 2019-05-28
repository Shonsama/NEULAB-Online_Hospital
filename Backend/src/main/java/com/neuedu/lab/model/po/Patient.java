package com.neuedu.lab.model.po;

import java.util.Date;

public class Patient {
    private String patient_record_id;
    private boolean patient_gender;
    private String patient_name;
    private String patient_credit_id;
    private Date patient_birthDate;
    private String patient_address;
    private Integer patient_age;

    public String getPatient_record_id() {
        return patient_record_id;
    }

    public void setPatient_record_id(String patient_record_id) {
        this.patient_record_id = patient_record_id;
    }

    public boolean isPatient_gender() {
        return patient_gender;
    }

    public void setPatient_gender(boolean patient_gender) {
        this.patient_gender = patient_gender;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_credit_id() {
        return patient_credit_id;
    }

    public void setPatient_credit_id(String patient_credit_id) {
        this.patient_credit_id = patient_credit_id;
    }

    public Date getPatient_birthDate() {
        return patient_birthDate;
    }

    public void setPatient_birthDate(Date patient_birthDate) {
        this.patient_birthDate = patient_birthDate;
    }

    public String getPatient_address() {
        return patient_address;
    }

    public void setPatient_address(String patient_address) {
        this.patient_address = patient_address;
    }

    public Integer getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(Integer patient_age) {
        this.patient_age = patient_age;
    }
}
