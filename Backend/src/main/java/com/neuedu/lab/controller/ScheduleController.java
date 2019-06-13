package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.Schedule;
import com.neuedu.lab.model.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/get-all")
    public JSONObject getAllSchedules(){
        return ConstantUtils.responseSuccess(scheduleService.getAllSchedules());
    }

    @RequestMapping("/add")
    public JSONObject addSchedule(@RequestBody Schedule schedule){
        return ConstantUtils.responseSuccess(scheduleService.addSchedule(schedule));
    }

    @RequestMapping("/delete")
    public JSONObject updateSchedules(@RequestBody Schedule schedule){
        return ConstantUtils.responseSuccess(scheduleService.updateSchedule(schedule));
    }

    @RequestMapping("/update")
    public JSONObject deleteSchedules(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(scheduleService.deleteSchedule(request.getInteger("schedule_id")));
    }



}
