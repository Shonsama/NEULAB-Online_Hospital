package com.neuedu.lab.model.mapper;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.po.Doctor;
import com.neuedu.lab.model.po.Rule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RuleMapper {
    public List<Rule> getAllRules();
    public void addRule(Rule rule);
    public void deleteRule(Integer rule_id);
    public void updateRule(Rule rule);
    public Rule getRule(String rule_id);
    public List<Doctor> getDoctors(@Param("department_id") String department_id, @Param("register_level_id") String register_level_id);
    public List<JSONObject> getNames();
}
