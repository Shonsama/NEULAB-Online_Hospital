package com.neuedu.lab.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.BeanInfo;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Daily {
    private Integer daily_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date daily_start;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date daily_end;


    private Date daily_operate_time;
    private Integer daily_user_id;
    private String daily_pass_state; //是否审核通过

    private Integer daily_owner_id;
    private BigDecimal daily_sum;
    private BigDecimal daily_actual_sum;
    private BigDecimal daily_mid_prescription_sum;
    private BigDecimal daily_west_prescription_sum;
    private BigDecimal daily_register_sum;
    private String daily_fail_comment;
    private BigDecimal daily_check_sum;
    private BigDecimal daily_examine_sum;
    private BigDecimal daily_handle_sum;
    private List<Bill> bills;
    private List<Bill> abandonBillList;//作废
    private List<Bill> redoBillList;//重打
    private List<Bill> overprintBillList;//补打
    private List<Bill> flushBillList;//对冲


    public List<Bill> getAbandonBillList() {
        return abandonBillList;
    }

    public void setAbandonBillList(List<Bill> abandonBillList) {
        this.abandonBillList = abandonBillList;
    }

    public List<Bill> getRedoBillList() {
        return redoBillList;
    }

    public void setRedoBillList(List<Bill> redoBillList) {
        this.redoBillList = redoBillList;
    }

    public List<Bill> getOverprintBillList() {
        return overprintBillList;
    }

    public void setOverprintBillList(List<Bill> overprintBillList) {
        this.overprintBillList = overprintBillList;
    }

    public List<Bill> getFlushBillList() {
        return flushBillList;
    }

    public void setFlushBillList(List<Bill> flushBillList) {
        this.flushBillList = flushBillList;
    }

    public BigDecimal getDaily_examine_sum() {
        return daily_examine_sum;
    }

    public void setDaily_examine_sum(BigDecimal daily_examine_sum) {
        this.daily_examine_sum = daily_examine_sum;
    }

    public BigDecimal getDaily_handle_sum() {
        return daily_handle_sum;
    }

    public void setDaily_handle_sum(BigDecimal daily_handle_sum) {
        this.daily_handle_sum = daily_handle_sum;
    }

    public BigDecimal getDaily_check_sum() {
        return daily_check_sum;
    }

    public void setDaily_check_sum(BigDecimal daily_check_sum) {
        this.daily_check_sum = daily_check_sum;
    }



    public String getDaily_fail_comment() {
        return daily_fail_comment;
    }

    public void setDaily_fail_comment(String daily_fail_comment) {
        this.daily_fail_comment = daily_fail_comment;
    }

    public BigDecimal getDaily_actual_sum() {
        return daily_actual_sum;
    }

    public void setDaily_actual_sum(BigDecimal daily_actual_sum) {
        this.daily_actual_sum = daily_actual_sum;
    }

    public BigDecimal getDaily_register_sum() {
        return daily_register_sum;
    }

    public void setDaily_register_sum(BigDecimal daily_register_sum) {
        this.daily_register_sum = daily_register_sum;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public Integer getDaily_owner_id() {
        return daily_owner_id;
    }

    public void setDaily_owner_id(Integer daily_owner_id) {
        this.daily_owner_id = daily_owner_id;
    }

    public BigDecimal getDaily_sum() {
        return daily_sum;
    }

    public void setDaily_sum(BigDecimal daily_sum) {
        this.daily_sum = daily_sum;
    }

    public BigDecimal getDaily_mid_prescription_sum() {
        return daily_mid_prescription_sum;
    }

    public void setDaily_mid_prescription_sum(BigDecimal daily_mid_prescription_sum) {
        this.daily_mid_prescription_sum = daily_mid_prescription_sum;
    }

    public BigDecimal getDaily_west_prescription_sum() {
        return daily_west_prescription_sum;
    }

    public void setDaily_west_prescription_sum(BigDecimal daily_west_prescription_sum) {
        this.daily_west_prescription_sum = daily_west_prescription_sum;
    }

    public Integer getDaily_id() {
        return daily_id;
    }

    public void setDaily_id(Integer daily_id) {
        this.daily_id = daily_id;
    }

    public Date getDaily_start() {
        return daily_start;
    }

    public void setDaily_start(Date daily_start) {
        this.daily_start = daily_start;
    }

    public Date getDaily_end() {
        return daily_end;
    }

    public void setDaily_end(Date daily_end) {
        this.daily_end = daily_end;
    }

    public Date getDaily_operate_time() {
        return daily_operate_time;
    }

    public void setDaily_operate_time(Date daily_operate_time) {
        this.daily_operate_time = daily_operate_time;
    }

    public Integer getDaily_user_id() {
        return daily_user_id;
    }

    public void setDaily_user_id(Integer daily_user_id) {
        this.daily_user_id = daily_user_id;
    }

    public String getDaily_pass_state() {
        return daily_pass_state;
    }

    public void setDaily_pass_state(String daily_pass_state) {
        this.daily_pass_state = daily_pass_state;
    }
}
