package com.neuedu.lab.controller;

import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.Constant;
import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.service.ConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/constant")
public class ConstantController {
    @Autowired
    private ConstantService constantService;

    @RequestMapping("/get-all")
    public JSONObject getAllConstant(){
        return ConstantUtils.responseSuccess(constantService.getAllConstant());
    }


    @RequestMapping("/get")
    public Object getConstant(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(constantService.getConstant(request.getString("constant_type")));
    }

    @RequestMapping("/add")
    public JSONObject addConstant(@RequestBody Constant constant){
        if(constantService.addConstant(constant)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update")
    public JSONObject updateConstant(@RequestBody Constant constant){
        if(constantService.updateConstant(constant)){
            return ConstantUtils.responseSuccess(null);        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/delete")
    public JSONObject deleteConstant(@RequestBody Constant constant){
        if(constantService.deleteConstant(constant)){
            return ConstantUtils.responseSuccess(null);        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }




}
