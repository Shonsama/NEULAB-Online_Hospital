package com.neuedu.lab.model.po;

public class Register {
    private Integer registerInfoId;
    private Integer registerInfoState;
    private Double registerInfoFee;
    private String registerInfoPayType;

    public Integer getRegisterInfoId() {
        return registerInfoId;
    }

    public void setRegisterInfoId(Integer registerInfoId) {
        this.registerInfoId = registerInfoId;
    }

    public Integer getRegisterInfoState() {
        return registerInfoState;
    }

    public void setRegisterInfoState(Integer registerInfoState) {
        this.registerInfoState = registerInfoState;
    }

    public Double getRegisterInfoFee() {
        return registerInfoFee;
    }

    public void setRegisterInfoFee(Double registerInfoFee) {
        this.registerInfoFee = registerInfoFee;
    }

    public String getRegisterInfoPayType() {
        return registerInfoPayType;
    }

    public void setRegisterInfoPayType(String registerInfoPayType) {
        this.registerInfoPayType = registerInfoPayType;
    }
}
