package com.neuedu.lab.model.po;

import java.util.List;

public class Constant {
    private int constant_id;
    private String constant_name;

    public int getConstant_id() {
        return constant_id;
    }

    public void setConstant_id(int constant_id) {
        this.constant_id = constant_id;
    }

    public String getConstant_name() {
        return constant_name;
    }

    public void setConstant_name(String constant_name) {
        this.constant_name = constant_name;
    }

    public String getConstant_type() {
        return constant_type;
    }

    public void setConstant_type(String constant_type) {
        this.constant_type = constant_type;
    }

    private String constant_type;
}
