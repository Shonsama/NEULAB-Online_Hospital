package com.neuedu.lab.model.po;

import java.math.BigDecimal;

public class RegisterLevel {
    private Integer register_level_id;
    private Integer register_level_seq_num;
    private String register_level_level;
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

    public String getRegister_level_level() {
        return register_level_level;
    }

    public void setRegister_level_level(String register_level_level) {
        this.register_level_level = register_level_level;
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
