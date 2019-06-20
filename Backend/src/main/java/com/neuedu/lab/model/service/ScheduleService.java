package com.neuedu.lab.model.service;

import com.neuedu.lab.model.mapper.ScheduleMapper;
import com.neuedu.lab.model.po.Schedule;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleService {
    @Resource
    private ScheduleMapper scheduleMapper;
    private List<Schedule> allSchedules;

    public List<Schedule> getAllSchedules(){
        allSchedules = scheduleMapper.getAllSchedules();
        return allSchedules;
    }

    public boolean addSchedule(Schedule schedule){
        try {
            getAllSchedules();
            scheduleMapper.addSchedule(schedule);
            for(Schedule temp:allSchedules){
                if ((temp.getSchedule_end_date().after(schedule.getSchedule_start_date()) || temp.getSchedule_end_date().compareTo(schedule.getSchedule_start_date()) == 0) &&
                        (temp.getSchedule_end_date().before(schedule.getSchedule_end_date()) || temp.getSchedule_end_date().compareTo(schedule.getSchedule_end_date()) == 0)
                && temp.getSchedule_start_date().before(schedule.getSchedule_start_date())){
                    temp.setSchedule_end_date(new Date(schedule.getSchedule_start_date().getTime()-1000*3600*24));
                    updateSchedule(temp);
                }
                else if(temp.getSchedule_end_date().after(schedule.getSchedule_end_date()) && temp.getSchedule_start_date().before(schedule.getSchedule_start_date())){
                    Schedule temp_right = new Schedule();
                    Schedule temp_left = new Schedule();
                    temp_right.setSchedule_start_date(new Date(schedule.getSchedule_end_date().getTime()+1000*3600*24));
                    temp_right.setSchedule_end_date(temp.getSchedule_end_date());
                    temp_right.setSchedule_doctor_id(temp.getSchedule_doctor_id());
                    temp_right.setSchedule_doctor_name(temp.getSchedule_doctor_name());
                    temp_right.setschedule_work_time(temp.getschedule_work_time());
                    temp_left.setSchedule_start_date(temp.getSchedule_start_date());
                    temp_left.setSchedule_end_date(new Date(schedule.getSchedule_start_date().getTime()-1000*3600*24));
                    temp_left.setSchedule_doctor_id(temp.getSchedule_doctor_id());
                    temp_left.setSchedule_doctor_name(temp.getSchedule_doctor_name());
                    temp_left.setschedule_work_time(temp.getschedule_work_time());
                    deleteSchedule(temp.getSchedule_id());
                    scheduleMapper.addSchedule(temp_left);
                    scheduleMapper.addSchedule(temp_right);
                }
                else if((temp.getSchedule_end_date().before(schedule.getSchedule_end_date()) || temp.getSchedule_end_date().compareTo(schedule.getSchedule_end_date()) == 0)
                        && (temp.getSchedule_start_date().after(schedule.getSchedule_start_date()) || temp.getSchedule_end_date().compareTo(schedule.getSchedule_start_date()) == 0)
                 ){
                    deleteSchedule(temp.getSchedule_id());
                }
                else if((temp.getSchedule_start_date().after(schedule.getSchedule_start_date()) || temp.getSchedule_start_date().compareTo(schedule.getSchedule_start_date()) ==0) &&
                        (temp.getSchedule_start_date().before(schedule.getSchedule_end_date()) || temp.getSchedule_start_date().compareTo(schedule.getSchedule_end_date()) == 0)
                        && temp.getSchedule_end_date().after(schedule.getSchedule_end_date())){
                    temp.setSchedule_start_date(new Date(schedule.getSchedule_end_date().getTime()+1000*3600*24));
                    updateSchedule(temp);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteSchedule(Integer schdule_id){
        try {
            scheduleMapper.deleteSchedule(schdule_id);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateSchedule(Schedule schedule){
        try {
            scheduleMapper.updateSchedule(schedule);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
