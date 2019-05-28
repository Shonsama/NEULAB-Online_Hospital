package com.neuedu.lab.model.po;

import java.math.BigDecimal;

public class Medicine {
    private String medicine_id;
    private String medicine_usage;
    private String medicine_name;
    private String medicine_specifications;
    private String medicine_unit;
    private String medicine_factory;
    private String medicine_formulation;
    private String medicine_type;
    private BigDecimal medicine_unit_price;
    private String medicine_pinyin;

    public String getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(String medicine_id) {
        this.medicine_id = medicine_id;
    }

    public String getMedicine_usage() {
        return medicine_usage;
    }

    public void setMedicine_usage(String medicine_usage) {
        this.medicine_usage = medicine_usage;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getMedicine_specifications() {
        return medicine_specifications;
    }

    public void setMedicine_specifications(String medicine_specifications) {
        this.medicine_specifications = medicine_specifications;
    }

    public String getMedicine_unit() {
        return medicine_unit;
    }

    public void setMedicine_unit(String medicine_unit) {
        this.medicine_unit = medicine_unit;
    }

    public String getMedicine_factory() {
        return medicine_factory;
    }

    public void setMedicine_factory(String medicine_factory) {
        this.medicine_factory = medicine_factory;
    }

    public String getMedicine_formulation() {
        return medicine_formulation;
    }

    public void setMedicine_formulation(String medicine_formulation) {
        this.medicine_formulation = medicine_formulation;
    }

    public String getMedicine_type() {
        return medicine_type;
    }

    public void setMedicine_type(String medicine_type) {
        this.medicine_type = medicine_type;
    }

    public BigDecimal getMedicine_unit_price() {
        return medicine_unit_price;
    }

    public void setMedicine_unit_price(BigDecimal medicine_unit_price) {
        this.medicine_unit_price = medicine_unit_price;
    }

    public String getMedicine_pinyin() {
        return medicine_pinyin;
    }

    public void setMedicine_pinyin(String medicine_pinyin) {
        this.medicine_pinyin = medicine_pinyin;
    }
}
