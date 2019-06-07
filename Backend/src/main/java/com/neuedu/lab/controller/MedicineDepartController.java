package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;

import com.neuedu.lab.model.service.MedicineDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/md-doctor")
public class MedicineDepartController {
    @Autowired
    MedicineDepartService medicineDepartService;

    @RequestMapping("/send-medicine")
    public JSONObject sendMedicine(@RequestBody JSONObject request){
        return medicineDepartService.sendMedicine(request.getInteger("prescription_id"));
    }

    @RequestMapping("/return-medicine")
    public JSONObject returnMedicine(@RequestBody JSONObject request){
        return medicineDepartService.returnMedicine(request.getInteger("prescription_id"),
                request.getString("prescription_medicine_id"),request.getInteger("prescription_num"));
    }










}
