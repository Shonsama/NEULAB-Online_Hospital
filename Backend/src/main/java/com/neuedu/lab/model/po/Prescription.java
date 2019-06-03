package com.neuedu.lab.model.po;

public class Prescription {
    private Integer prescription_id;
    private String prescription_execute_state;
    private String prescription_type;
    private Integer prescription_doctor_id;
    private Integer prescription_register_info_id;
    private Integer prescription_user_id;

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
