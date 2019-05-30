package com.neuedu.lab.controller;

import com.neuedu.lab.model.po.Constant;
import com.neuedu.lab.model.service.ConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/constant")
public class ConstantController {
    @Autowired
    private ConstantService constantService;

    @RequestMapping("/get-all")
    public List<Constant> getAllConstant(){
        return constantService.getAllConstant();
    }


    @RequestMapping("/get")
    public void getConstant(@RequestParam String constant_type){
        constantService.getConstant(constant_type);
    }

    @RequestMapping("/add")
    public String addConstant(@RequestParam Constant constant){
        if(constantService.addConstant(constant)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/update")
    public String updateConstant(@RequestParam Constant constant){
        if(constantService.updateConstant(constant)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/delete")
    public String deleteConstant(@RequestParam Constant constant){
        if(constantService.deleteConstant(constant)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }




}
