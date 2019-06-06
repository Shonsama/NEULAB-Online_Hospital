package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.FeeCat;
import com.neuedu.lab.model.service.FeeCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fee-cat")
public class FeeCatController {
    @Autowired
    private FeeCatService feeCatService;

    @RequestMapping("/get")
    public Object getFeeCat(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(feeCatService.getFeeCat(request.getString("fee_cat_id")));
    }

    @RequestMapping("/get-all")
    public JSONObject getAllFeeCats(){
        return ConstantUtils.responseSuccess(feeCatService.getAllFeeCats());
    }

    @RequestMapping("/add")
    public JSONObject addFeeCat(@RequestBody FeeCat feeCat){
        if(feeCatService.addFeeCat(feeCat)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update")
    public JSONObject updateFeeCat(@RequestBody FeeCat feeCat){
        if(feeCatService.updateFeeCat(feeCat)){
            return ConstantUtils.responseSuccess(null);        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/delete")
    public JSONObject deleteFeeCat(@RequestBody JSONObject request){
        if(feeCatService.deleteFeeCat(request.getString("fee_cat_id"))){
            return ConstantUtils.responseSuccess(null);        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }
}
