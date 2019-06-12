package com.neuedu.lab.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Bill {
    private Integer bill_id;
    private BigDecimal bill_sum;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bill_time;
    private String bill_type;
    private Integer bill_register_id;
    private Integer bill_prescription_id;
    private Integer bill_medical_skill_id;
    private Integer bill_user_id;
    private String bill_state;
    private String bill_fee_cat_name;
    private String bill_num;

    public String getBill_num() {
        return bill_num;
    }

    public void setBill_num(String bill_num) {
        this.bill_num = bill_num;
    }

    public Integer getBill_prescription_id() {
        return bill_prescription_id;
    }

    public void setBill_prescription_id(Integer bill_prescription_id) {
        this.bill_prescription_id = bill_prescription_id;
    }

    public String getBill_fee_cat_name() {
        return bill_fee_cat_name;
    }

    public void setBill_fee_cat_name(String bill_fee_cat_name) {
        this.bill_fee_cat_name = bill_fee_cat_name;
    }

    public Integer getBill_id() {
        return bill_id;
    }

    public void setBill_id(Integer bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_state() {
        return bill_state;
    }

    public void setBill_state(String bill_state) {
        this.bill_state = bill_state;
    }

    public BigDecimal getBill_sum() {
        return bill_sum;
    }

    public void setBill_sum(BigDecimal bill_sum) {
        this.bill_sum = bill_sum;
    }

    public Date getBill_time() {
        return bill_time;
    }

    public void setBill_time(Date bill_time) {
        this.bill_time = bill_time;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public Integer getBill_register_id() {
        return bill_register_id;
    }

    public void setBill_register_id(Integer bill_register_id) {
        this.bill_register_id = bill_register_id;
    }

    public Integer getBill_perscription_id() {
        return bill_prescription_id;
    }

    public void setBill_perscription_id(Integer bill_perscription_id) {
        this.bill_prescription_id = bill_perscription_id;
    }

    public Integer getBill_medical_skill_id() {
        return bill_medical_skill_id;
    }

    public void setBill_medical_skill_id(Integer bill_medical_skill_id) {
        this.bill_medical_skill_id = bill_medical_skill_id;
    }

    public Integer getBill_user_id() {
        return bill_user_id;
    }

    public void setBill_user_id(Integer bill_user_id) {
        this.bill_user_id = bill_user_id;
    }
}
