package com.neuedu.lab.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Schedule {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date schedule_start_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date schedule_end_date;
    private String rule_work_time;
    private String schedule_doctor_name;
    private Integer schedule_id;
    private Integer schedule_doctor_id;

    public String getSchedule_doctor_name() {
        return schedule_doctor_name;
    }

    public void setSchedule_doctor_name(String schedule_doctor_name) {
        this.schedule_doctor_name = schedule_doctor_name;
    }

    public String getRule_work_time() {
        return rule_work_time;
    }

    public void setRule_work_time(String rule_work_time) {
        this.rule_work_time = rule_work_time;
    }

    public Date getSchedule_start_date() {
        return schedule_start_date;
    }

    public void setSchedule_start_date(Date schedule_start_date) {
        this.schedule_start_date = schedule_start_date;
    }

    public Date getSchedule_end_date() {
        return schedule_end_date;
    }

    public void setSchedule_end_date(Date schedule_end_date) {
        this.schedule_end_date = schedule_end_date;
    }

    public Integer getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Integer schedule_id) {
        this.schedule_id = schedule_id;
    }

    public Integer getSchedule_doctor_id() {
        return schedule_doctor_id;
    }

    public void setSchedule_doctor_id(Integer schedule_doctor_id) {
        this.schedule_doctor_id = schedule_doctor_id;
    }
}
