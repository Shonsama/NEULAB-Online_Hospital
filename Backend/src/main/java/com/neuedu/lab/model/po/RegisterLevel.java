package com.neuedu.lab.model.po;

import java.math.BigDecimal;

public class RegisterLevel {
    private Integer register_level_id;
    private Integer register_level_seq_num;
    private String register_level_name;
    private Integer register_level_max;
    private BigDecimal register_level_fee;

    public Integer getRegister_level_id() {
        return register_level_id;
    }

    public void setRegister_level_id(Integer register_level_id) {
        this.register_level_id = register_level_id;
    }

    public Integer getRegister_level_seq_num() {
        return register_level_seq_num;
    }

    public void setRegister_level_seq_num(Integer register_level_seq_num) {
        this.register_level_seq_num = register_level_seq_num;
    }

    public String getRegister_level_name() {
        return register_level_name;
    }

    public void setRegister_level_name(String register_level_name) {
        this.register_level_name = register_level_name;
    }

    public Integer getRegister_level_max() {
        return register_level_max;
    }

    public void setRegister_level_max(Integer register_level_max) {
        this.register_level_max = register_level_max;
    }

    public BigDecimal getRegister_level_fee() {
        return register_level_fee;
    }

    public void setRegister_level_fee(BigDecimal register_level_fee) {
        this.register_level_fee = register_level_fee;
    }
}
