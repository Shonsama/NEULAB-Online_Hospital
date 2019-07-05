package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.service.WorkloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/workload")
public class WorkloadController {

    @Autowired
    private WorkloadService workloadService;

    @RequestMapping("/get-doctor")
    public JSONObject getDoctorsWorkload(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {

        return workloadService.getDoctorsWorkload(request.getDate("start_time"), request.getDate("end_time"));

    }

    @RequestMapping("/get-department-draw")
    public JSONObject getDepartmentsWorkloadDraw(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        return workloadService.getDepartmentsWorkload(request.getDate("start_time"), request.getDate("end_time"),0);
    }

    @RequestMapping("/get-department-execute")
    public JSONObject getDepartmentsWorkloadExecute(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        return workloadService.getDepartmentsWorkload(request.getDate("start_time"), request.getDate("end_time"),1);
    }
}

