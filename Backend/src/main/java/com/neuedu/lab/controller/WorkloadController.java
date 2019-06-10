package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.service.DoctorWorkloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workload")
public class WorkloadController {

    @Autowired
    private DoctorWorkloadService doctorWorkloadService;

    @RequestMapping("/get-doctor")
    public JSONObject getDoctorWorkload(@RequestBody JSONObject request){
        return doctorWorkloadService.getDoctorsWorkload(request.getDate("start_time"),request.getDate("end_time"));
    }
}
