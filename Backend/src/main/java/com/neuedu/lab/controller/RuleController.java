package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.Rule;
import com.neuedu.lab.model.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rule")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @RequestMapping("/get-all")
//    显示所有科室信息
    public JSONObject getAllRules(){
        return ConstantUtils.responseSuccess(ruleService.getAllRules());
    }

    @RequestMapping("/get")
//    显示查询科室信息
    public JSONObject getRule(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(ruleService.getRule(request.getString("rule_id")));
    }

    @RequestMapping("/get-department_level_doctor")
//    显示查询科室信息
    public JSONObject getDoctors(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(ruleService.getDoctors(request.getString("department_id"),request.getString("register_level_id")));
    }

    @RequestMapping("/get-all-names")
//    显示查询科室信息
    public JSONObject getNames(){
        return ConstantUtils.responseSuccess(ruleService.getNames());
    }

    @RequestMapping("/add")
    public JSONObject addRule(@RequestBody Rule rule){
        if(ruleService.addRule(rule)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update")
    public JSONObject updateRule(@RequestBody Rule rule){
        if(ruleService.updateRule(rule)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/delete")
    public JSONObject deleteRule(@RequestBody JSONObject rule_id){
        Integer getString = rule_id.getInteger("rule_id");
        if(ruleService.deleteRule(getString)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }
}