package com.neuedu.lab.model.mapper;


import com.neuedu.lab.model.po.Daily;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DailyMapper {
    Daily getDailyByUserId(Integer daily_user_id);
    List<Daily> getDaily(Daily daily);
    void addDaily(Daily daily);
    void updateDailyPass(Integer daily_id, boolean daily_pass);
}
