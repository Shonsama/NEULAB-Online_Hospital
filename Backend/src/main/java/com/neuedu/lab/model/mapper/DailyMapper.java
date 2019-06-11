package com.neuedu.lab.model.mapper;


import com.neuedu.lab.model.po.Daily;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DailyMapper {
    Daily getDailyByUserId(Integer daily_user_id);
    List<Daily> getDaily(Daily daily);
    void addDaily(Daily daily);
    void updateDailyPass(@Param("daily_id") Integer daily_id,
                         @Param("daily_pass_state") String  daily_pass_state,
                         @Param("daily_owner_id") Integer daily_owner_id);

    //
    Daily getDailyById(Integer daily_id);

}
