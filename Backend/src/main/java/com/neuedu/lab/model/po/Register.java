package com.neuedu.lab.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Register {
    private Integer register_info_id;
    private String register_info_state;
    private BigDecimal register_info_fee;
    private String register_info_pay_type;
    private Integer register_info_doctor_id;
    private Integer register_info_patient_id;
    private Integer register_info_user_id;
    private Boolean register_info_records_book;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date register_info_time;
    private Doctor doctor;
    private Patient patient;
    private User user;

    public Date getRegister_info_time() {
        return register_info_time;
    }

    public void setRegister_info_time(Date register_info_time) {
        this.register_info_time = register_info_time;
    }

    public Boolean getRegister_info_records_book() {
        return register_info_records_book;
    }

    public void setRegister_info_records_book(Boolean register_info_records_book) {
        this.register_info_records_book = register_info_records_book;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Integer getRegister_info_id() {
        return register_info_id;
    }

    public void setRegister_info_id(Integer register_info_id) {
        this.register_info_id = register_info_id;
    }

    public String getRegister_info_state() {
        return register_info_state;
    }

    public void setRegister_info_state(String register_info_state) {
        this.register_info_state = register_info_state;
    }

    public BigDecimal getRegister_info_fee() {
        return register_info_fee;
    }

    public void setRegister_info_fee(BigDecimal register_info_fee) {
        this.register_info_fee = register_info_fee;
    }

    public String getRegister_info_pay_type() {
        return register_info_pay_type;
    }

    public void setRegister_info_pay_type(String register_info_pay_type) {
        this.register_info_pay_type = register_info_pay_type;
    }

    public Integer getRegister_info_doctor_id() {
        return register_info_doctor_id;
    }

    public void setRegister_info_doctor_id(Integer register_info_doctor_id) {
        this.register_info_doctor_id = register_info_doctor_id;
    }

    public Integer getRegister_info_patient_id() {
        return register_info_patient_id;
    }

    public void setRegister_info_patient_id(Integer register_info_patient_id) {
        this.register_info_patient_id = register_info_patient_id;
    }

    public Integer getRegister_info_user_id() {
        return register_info_user_id;
    }

    public void setRegister_info_user_id(Integer register_info_user_id) {
        this.register_info_user_id = register_info_user_id;
    }
}