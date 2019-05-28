package com.neuedu.lab.model.po;

import java.math.BigDecimal;

public class NonMedicine {
    private String non_medicine_id;
    private String non_medicine_type;
    private String non_medicine_name;
    private String non_medicine_specification;
    private BigDecimal non_medicine_unit_price;

    public String getNon_medicine_id() {
        return non_medicine_id;
    }

    public void setNon_medicine_id(String non_medicine_id) {
        this.non_medicine_id = non_medicine_id;
    }

    public String getNon_medicine_type() {
        return non_medicine_type;
    }

    public void setNon_medicine_type(String non_medicine_type) {
        this.non_medicine_type = non_medicine_type;
    }

    public String getNon_medicine_name() {
        return non_medicine_name;
    }

    public void setNon_medicine_name(String non_medicine_name) {
        this.non_medicine_name = non_medicine_name;
    }

    public String getNon_medicine_specification() {
        return non_medicine_specification;
    }

    public void setNon_medicine_specification(String non_medicine_specification) {
        this.non_medicine_specification = non_medicine_specification;
    }

    public BigDecimal getNon_medicine_unit_price() {
        return non_medicine_unit_price;
    }

    public void setNon_medicine_unit_price(BigDecimal non_medicine_unit_price) {
        this.non_medicine_unit_price = non_medicine_unit_price;
    }
}
