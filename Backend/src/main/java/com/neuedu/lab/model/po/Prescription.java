package com.neuedu.lab.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Prescription {
    private Integer prescription_id;
    private String prescription_execute_state;
    private String prescription_type;
    private Integer prescription_doctor_id;
    private Integer prescription_register_info_id;
    private Integer prescription_user_id;
    private BigDecimal prescription_fee;
    private String prescription_name;
    private List<PrescriptionContent> prescriptionContents;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date prescription_create_time;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date prescription_pay_time;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date prescription_sent_time;
    private Integer prescription_execute_doctor_id;
    private Doctor prescription_execute_doctor;

    public Doctor getPrescription_execute_doctor() {
        return prescription_execute_doctor;
    }

    public void setPrescription_execute_doctor(Doctor prescription_execute_doctor) {
        this.prescription_execute_doctor = prescription_execute_doctor;
    }

    public Integer getPrescription_execute_doctor_id() {
        return prescription_execute_doctor_id;
    }

    public void setPrescription_execute_doctor_id(Integer prescription_execute_doctor_id) {
        this.prescription_execute_doctor_id = prescription_execute_doctor_id;
    }

    public Date getPrescription_create_time() {
        return prescription_create_time;
    }

    public void setPrescription_create_time(Date prescription_create_time) {
        this.prescription_create_time = prescription_create_time;
    }

    public Date getPrescription_pay_time() {
        return prescription_pay_time;
    }

    public void setPrescription_pay_time(Date prescription_pay_time) {
        this.prescription_pay_time = prescription_pay_time;
    }

    public Date getPrescription_sent_time() {
        return prescription_sent_time;
    }

    public void setPrescription_sent_time(Date prescription_sent_time) {
        this.prescription_sent_time = prescription_sent_time;
    }

    public List<PrescriptionContent> getPrescriptionContents() {
        return prescriptionContents;
    }

    public void setPrescriptionContents(List<PrescriptionContent> prescriptionContents) {
        this.prescriptionContents = prescriptionContents;
    }

    public String getPrescription_name() {
        return prescription_name;
    }

    public void setPrescription_name(String prescription_name) {
        this.prescription_name = prescription_name;
    }

    public BigDecimal getPrescription_fee() {
        return prescription_fee;
    }

    public void setPrescription_fee(BigDecimal prescription_fee) {
        this.prescription_fee = prescription_fee;
    }

    public Integer getPrescription_user_id() {
        return prescription_user_id;
    }

    public void setPrescription_user_id(Integer prescription_user_id) {
        this.prescription_user_id = prescription_user_id;
    }

    public Integer getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(Integer prescription_id) {
        this.prescription_id = prescription_id;
    }

    public String getPrescription_execute_state() {
        return prescription_execute_state;
    }

    public void setPrescription_execute_state(String prescription_execute_state) {
        this.prescription_execute_state = prescription_execute_state;
    }

    public String getPrescription_type() {
        return prescription_type;
    }

    public void setPrescription_type(String prescription_type) {
        this.prescription_type = prescription_type;
    }

    public Integer getPrescription_doctor_id() {
        return prescription_doctor_id;
    }

    public void setPrescription_doctor_id(Integer prescription_doctor_id) {
        this.prescription_doctor_id = prescription_doctor_id;
    }

    public Integer getPrescription_register_info_id() {
        return prescription_register_info_id;
    }

    public void setPrescription_register_info_id(Integer prescription_register_info_id) {
        this.prescription_register_info_id = prescription_register_info_id;
    }


}
