package com.neuedu.lab.model.po;

import java.math.BigDecimal;

public class PrescriptionContent {
    private Integer prescription_content_id;
    private Integer prescription_id;
    private String prescription_consumption;
    private String prescription_medicine_id;
    private String prescription_frequency;
    private Integer prescription_num;
    private Integer prescription_day;
    private BigDecimal prescription_unit_price;
    private BigDecimal prescription_content_fee;
    private BigDecimal prescription_content_unit_actual_price;

    public BigDecimal getPrescription_content_unit_actual_price() {
        return prescription_content_unit_actual_price;
    }

    public void setPrescription_content_unit_actual_price(BigDecimal prescription_content_unit_actual_price) {
        this.prescription_content_unit_actual_price = prescription_content_unit_actual_price;
    }

    public BigDecimal getPrescription_unit_price() {
        return prescription_unit_price;
    }

    public void setPrescription_unit_price(BigDecimal prescription_unit_price) {
        this.prescription_unit_price = prescription_unit_price;
    }

    public BigDecimal getPrescription_content_fee() {
        return prescription_content_fee;
    }

    public void setPrescription_content_fee(BigDecimal prescription_content_fee) {
        this.prescription_content_fee = prescription_content_fee;
    }

    public Integer getPrescription_content_id() {
        return prescription_content_id;
    }

    public void setPrescription_content_id(Integer prescription_content_id) {
        this.prescription_content_id = prescription_content_id;
    }

    public Integer getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(Integer prescription_id) {
        this.prescription_id = prescription_id;
    }

    public String getPrescription_consumption() {
        return prescription_consumption;
    }

    public void setPrescription_consumption(String prescription_consumption) {
        this.prescription_consumption = prescription_consumption;
    }

    public String getPrescription_medicine_id() {
        return prescription_medicine_id;
    }

    public void setPrescription_medicine_id(String prescription_medicine_id) {
        this.prescription_medicine_id = prescription_medicine_id;
    }

    public String getPrescription_frequency() {
        return prescription_frequency;
    }

    public void setPrescription_frequency(String prescription_frequency) {
        this.prescription_frequency = prescription_frequency;
    }

    public Integer getPrescription_num() {
        return prescription_num;
    }

    public void setPrescription_num(Integer prescription_num) {
        this.prescription_num = prescription_num;
    }

    public Integer getPrescription_day() {
        return prescription_day;
    }

    public void setPrescription_day(Integer prescription_day) {
        this.prescription_day = prescription_day;
    }
}
