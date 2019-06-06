package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//import net.sf.json.JSONArray;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private PayService payService;

    //缴费部分
    @RequestMapping("/medical-skill")
    public JSONObject payMedicalSkill(@RequestBody JSONArray medical_skill_ids) {
        List<Integer> ids = JSONObject.parseArray(medical_skill_ids.toJSONString(),Integer.class);
        return payService.payMedicalSkillFee(ids);
    }

    @RequestMapping("/prescription")
    public JSONObject payPrescription(@RequestBody JSONArray prescription_ids){
        List<Integer> ids = JSONObject.parseArray(prescription_ids.toJSONString(),Integer.class);
        return payService.payPrescriptionFee(ids);
    }
}
