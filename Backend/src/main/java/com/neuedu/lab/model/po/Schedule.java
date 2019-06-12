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
    private Integer schedule_id;
    private Integer schedule_doctor_id;

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
