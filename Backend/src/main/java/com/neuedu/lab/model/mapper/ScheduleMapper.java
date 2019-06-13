package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Schedule;

import java.util.List;

public interface ScheduleMapper {
    public List<Schedule> getAllSchedules();
    public void addSchedule(Schedule schedule);
    public void deleteSchedule(Integer schedule_id);
    public void updateSchedule(Schedule schedule);
}
