package com.neuedu.lab.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class MedicalSkill {
    private Integer medical_skill_id;
    private String medical_skill_execute_state;
    private String medical_skill_result;
    private String medical_skill_type;
    private String medical_skill_name;
    private String medical_skill_checkpoint;
    private String medical_skill_purpose;
    private boolean medical_skill_urgent;
    private Date medical_skill_create_time;
    private Date medical_skill_pay_time;
    private Date medical_skill_consume_time;
    private BigDecimal medical_skill_fee;
    private Integer medical_skill_register_info_id;
    private Integer medical_skill_user_id;
    private Integer medical_skill_doctor_id;
    private String medical_skill_content_id;
    private MedicalSkillContent medicalSkillContent;

    public MedicalSkillContent getMedicalSkillContent() {
        return medicalSkillContent;
    }

    public void setMedicalSkillContent(MedicalSkillContent medicalSkillContent) {
        this.medicalSkillContent = medicalSkillContent;
    }

    public Integer getMedical_skill_execute_doctor_id() {
        return medical_skill_execute_doctor_id;
    }

    public void setMedical_skill_execute_doctor_id(Integer medical_skill_execute_doctor_id) {
        this.medical_skill_execute_doctor_id = medical_skill_execute_doctor_id;
    }

    private Integer medical_skill_execute_doctor_id;



    public String getMedical_skill_content_id() {
        return medical_skill_content_id;
    }

    public void setMedical_skill_content_id(String medical_skill_content_id) {
        this.medical_skill_content_id = medical_skill_content_id;
    }
    private Register register;
    private String medical_skill_execute_department;

    public String getMedical_skill_execute_department() {
        return medical_skill_execute_department;
    }

    public void setMedical_skill_execute_department(String medical_skill_execute_department) {
        this.medical_skill_execute_department = medical_skill_execute_department;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public Date getMedical_skill_create_time() {
        return medical_skill_create_time;
    }

    public void setMedical_skill_create_time(Date medical_skill_create_time) {
        this.medical_skill_create_time = medical_skill_create_time;
    }

    public Date getMedical_skill_pay_time() {
        return medical_skill_pay_time;
    }

    public void setMedical_skill_pay_time(Date medical_skill_pay_time) {
        this.medical_skill_pay_time = medical_skill_pay_time;
    }

    public Date getMedical_skill_consume_time() {
        return medical_skill_consume_time;
    }

    public void setMedical_skill_consume_time(Date medical_skill_consume_time) {
        this.medical_skill_consume_time = medical_skill_consume_time;
    }

    private String medical_skill_non_medical_id;

    private NonMedicine nonMedicine;

    public NonMedicine getNonMedicine() {
        return nonMedicine;
    }

    public void setNonMedicine(NonMedicine nonMedicine) {
        this.nonMedicine = nonMedicine;
    }

    public String getMedical_skill_execute_state() {
        return medical_skill_execute_state;
    }

    public void setMedical_skill_execute_state(String medical_skill_execute_state) {
        this.medical_skill_execute_state = medical_skill_execute_state;
    }

    public BigDecimal getMedical_skill_fee() {
        return medical_skill_fee;
    }

    public void setMedical_skill_fee(BigDecimal medical_skill_fee) {
        this.medical_skill_fee = medical_skill_fee;
    }

    public Integer getMedical_skill_id() {
        return medical_skill_id;
    }

    public void setMedical_skill_id(Integer medical_skill_id) {
        this.medical_skill_id = medical_skill_id;
    }


    public String getMedical_skill_result() {
        return medical_skill_result;
    }

    public void setMedical_skill_result(String medical_skill_result) {
        this.medical_skill_result = medical_skill_result;
    }

    public String getMedical_skill_type() {
        return medical_skill_type;
    }

    public void setMedical_skill_type(String medical_skill_type) {
        this.medical_skill_type = medical_skill_type;
    }

    public String getMedical_skill_name() {
        return medical_skill_name;
    }

    public void setMedical_skill_name(String medical_skill_name) {
        this.medical_skill_name = medical_skill_name;
    }

    public String getMedical_skill_checkpoint() {
        return medical_skill_checkpoint;
    }

    public void setMedical_skill_checkpoint(String medical_skill_checkpoint) {
        this.medical_skill_checkpoint = medical_skill_checkpoint;
    }

    public String getMedical_skill_purpose() {
        return medical_skill_purpose;
    }

    public void setMedical_skill_purpose(String medical_skill_purpose) {
        this.medical_skill_purpose = medical_skill_purpose;
    }

    public boolean isMedical_skill_urgent() {
        return medical_skill_urgent;
    }

    public void setMedical_skill_urgent(boolean medical_skill_urgent) {
        this.medical_skill_urgent = medical_skill_urgent;
    }

    public Integer getMedical_skill_register_info_id() {
        return medical_skill_register_info_id;
    }

    public void setMedical_skill_register_info_id(Integer medical_skill_register_info_id) {
        this.medical_skill_register_info_id = medical_skill_register_info_id;
    }

    public Integer getMedical_skill_user_id() {
        return medical_skill_user_id;
    }

    public void setMedical_skill_user_id(Integer medical_skill_user_id) {
        this.medical_skill_user_id = medical_skill_user_id;
    }

    public Integer getMedical_skill_doctor_id() {
        return medical_skill_doctor_id;
    }

    public void setMedical_skill_doctor_id(Integer medical_skill_doctor_id) {
        this.medical_skill_doctor_id = medical_skill_doctor_id;
    }

    public String getMedical_skill_non_medical_id() {
        return medical_skill_non_medical_id;
    }

    public void setMedical_skill_non_medical_id(String medical_skill_non_medical_id) {
        this.medical_skill_non_medical_id = medical_skill_non_medical_id;
    }
}
