package com.neuedu.lab.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Diagnose {
    private Integer diagnose_id;
    private Integer diagnose_record_id;
    private String diagnose_type;
    private String diagnose_disease_id;
    private String diagnose_disease_name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Date diagnose_time;


    public Integer getDiagnose_id() {
        return diagnose_id;
    }

    public void setDiagnose_id(Integer diagnose_id) {
        this.diagnose_id = diagnose_id;
    }

    public Integer getDiagnose_record_id() {
        return diagnose_record_id;
    }

    public void setDiagnose_record_id(Integer diagnose_record_id) {
        this.diagnose_record_id = diagnose_record_id;
    }

    public String getDiagnose_type() {
        return diagnose_type;
    }

    public void setDiagnose_type(String diagnose_type) {
        this.diagnose_type = diagnose_type;
    }

    public String getDiagnose_disease_id() {
        return diagnose_disease_id;
    }

    public void setDiagnose_disease_id(String diagnose_disease_id) {
        this.diagnose_disease_id = diagnose_disease_id;
    }

    public String getDiagnose_disease_name() {
        return diagnose_disease_name;
    }

    public void setDiagnose_disease_name(String diagnose_disease_name) {
        this.diagnose_disease_name = diagnose_disease_name;
    }

    public Date getDiagnose_time() {
        return diagnose_time;
    }

    public void setDiagnose_time(Date diagnose_time) {
        this.diagnose_time = diagnose_time;
    }
}
