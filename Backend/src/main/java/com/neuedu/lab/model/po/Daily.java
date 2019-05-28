package com.neuedu.lab.model.po;

import java.util.Date;

public class Daily {
    private Integer daily_id;
    private Date daily_start;
    private Date daily_end;
    private Date daily_operate_time;
    private Integer daily_user_id;
    private boolean daily_pass; //是否审核通过

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

    public boolean isDaily_pass() {
        return daily_pass;
    }

    public void setDaily_pass(boolean daily_pass) {
        this.daily_pass = daily_pass;
    }
}
