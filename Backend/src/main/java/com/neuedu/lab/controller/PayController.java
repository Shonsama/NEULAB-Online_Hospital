package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.Bill;
import com.neuedu.lab.model.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import net.sf.json.JSONArray;

/**
 * 给收费员的接口
 */
@RestController
@RequestMapping("/pay")
public class PayController {
    Bill bill;
    @Autowired
    private PayService payService;

    //缴费部分
/*    @RequestMapping("/medical-skill")
    public JSONObject payMedicalSkill(@RequestBody JSONArray medical_skill_ids) {
        List<Integer> ids = JSONObject.parseArray(medical_skill_ids.toJSONString(),Integer.class);
        return payService.payMedicalSkillFee(ids);
    }

    @RequestMapping("/prescription")
    public JSONObject payPrescription(@RequestBody JSONArray prescription_ids){
        List<Integer> ids = JSONObject.parseArray(prescription_ids.toJSONString(),Integer.class);
        return payService.payPrescriptionFee(ids);
    }*/

    @RequestMapping("")
    public JSONObject payFee(@RequestBody JSONObject request){
        return payService.payFee(request.getInteger("id"), request.getString("type"),
                request.getInteger("register_id"),request.getInteger("user_id"));
    }

    //已缴费/已取消执行
    @RequestMapping("/get-medical-skill-canceled-or-paid")
    public JSONObject getMedicalSkillsInCanceledOrPaid(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(payService.getMedicalSkillsInCanceledOrPaid(request.getInteger("register_info_patient_id")));
    }
    //已开立
    @RequestMapping("/get-medical-skill-drew")
    public JSONObject getMedicalSkillsInDrew(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(payService.getMedicalSkillsInDrew(request.getInteger("register_info_patient_id")));
    }

    //已缴费/已退药
    @RequestMapping("/get-prescription-returned-or-paid")
    public JSONObject getPrescriptionsInReturnedOrPaid(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(payService.getPrescriptionsInReturnedOrPaid(request.getInteger("register_info_patient_id")));
    }

    //已发送
    @RequestMapping("/get-prescription-sent")
    public JSONObject getPrescriptionsInSent(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(payService.getPrescriptionsInSent(request.getInteger("register_info_patient_id")));
    }
}
