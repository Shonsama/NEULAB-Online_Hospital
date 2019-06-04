package com.neuedu.lab.controller;
import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.RegisterLevel;
import com.neuedu.lab.model.service.RegisterLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registerLevel")
public class RegisterLevelController {
    @Autowired
    private RegisterLevelService registerLevelService;
    @RequestMapping("/get-all")
    public JSONObject getAllRegisterLevels(){
        return ConstantUtils.responseSuccess(registerLevelService.getAllRegisterLevels());
    }
    @RequestMapping("/get")
//    显示查询科室信息
    public JSONObject getRegisterLevel(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(registerLevelService.getRegisterLevel(request.getInteger("register_level_id")));
    }

    @RequestMapping("/add")
    public JSONObject addRegisterLevel(@RequestBody RegisterLevel registerLevel){
        if(registerLevelService.addRegisterLevel(registerLevel)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update")
    public JSONObject updateRegisterLevel(@RequestBody RegisterLevel registerLevel){
        if(registerLevelService.updateRegisterLevel(registerLevel)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/delete")
    public JSONObject deleteRegisterLevel(@RequestBody JSONObject request){
        Integer getInteger = request.getInteger("register_level_id");
        if(registerLevelService.deleteRegisterLevel(getInteger)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

}
