package com.neuedu.lab.model.mapper;


import com.neuedu.lab.model.po.Daily;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DailyMapper {
    //获取最近一次被审核过的记录
    Daily getDailyByUserId(@Param("daily_user_id") Integer daily_user_id,
                           @Param("daily_pass_state0") String daily_pass_state0,
                           @Param("daily_pass_state1") String daily_pass_state1,
                           @Param("daily_pass_state2") String daily_pass_state2);

    //删除未被审核的记录
    void  deleteNonPassDaily(@Param("daily_user_id") Integer daily_user_id,
                             @Param("daily_pass_state") String daily_pass_state);

    List<Daily> getDaily(Daily daily);
    void addDaily(Daily daily);
    void updateDailyPass(@Param("daily_id") Integer daily_id,
                         @Param("daily_pass_state") String  daily_pass_state,
                         @Param("daily_owner_id") Integer daily_owner_id);

    //
    Daily getDailyById(Integer daily_id);

}
