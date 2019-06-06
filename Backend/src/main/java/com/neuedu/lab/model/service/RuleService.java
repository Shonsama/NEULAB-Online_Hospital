package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.mapper.RuleMapper;
import com.neuedu.lab.model.po.Doctor;
import com.neuedu.lab.model.po.Rule;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RuleService {
    @Resource
    private RuleMapper ruleMapper;


    public List<Rule> getAllRules(){
        return ruleMapper.getAllRules();
    }

    public Rule getRule(String rule_id){
        return ruleMapper.getRule(rule_id);
    }

    public List<JSONObject> getNames() {
        return ruleMapper.getNames();
    }

    public List<Doctor> getDoctors(String department_id,String register_level_id){
        return ruleMapper.getDoctors(department_id,register_level_id);
    }

    public boolean addRule(Rule rule){
        try {
            ruleMapper.addRule(rule);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteRule(Integer rule_id){
        try {
            ruleMapper.deleteRule(rule_id);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateRule(Rule Rule){
        try {
            ruleMapper.updateRule(Rule);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }



}
