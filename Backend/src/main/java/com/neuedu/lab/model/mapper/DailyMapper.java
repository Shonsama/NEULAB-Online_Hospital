package com.neuedu.lab.model.mapper;


import com.neuedu.lab.model.po.Daily;

public interface DailyMapper {
    Daily getDailyByUserId(Integer user_id);
    void addDaily(Daily daily);
    void updateDailyPass(Integer daily_id, boolean daily_pass);
}
