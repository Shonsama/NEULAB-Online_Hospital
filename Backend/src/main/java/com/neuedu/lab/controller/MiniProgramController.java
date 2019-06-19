package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mp")
public class MiniProgramController {
    @Autowired
    private PatientService patientService;

    @RequestMapping("/login")
    public JSONObject checkUserValid(@RequestBody JSONObject request){
        return patientService.checkUserValid(request.getString("patient_account"),request.getString("patient_password"));
    }
}
