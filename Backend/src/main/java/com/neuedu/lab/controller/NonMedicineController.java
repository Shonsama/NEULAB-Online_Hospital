package com.neuedu.lab.controller;


import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.NonMedicine;
import com.neuedu.lab.model.service.NonMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/non-medicine")
public class NonMedicineController {
    @Autowired
    private NonMedicineService nonMedicineService;

    @RequestMapping("/get-all")
//    返回所有非药品信息
    public List<NonMedicine> getAllNonMedicines(){
        return nonMedicineService.getAllNonMedicines();
    }

    @RequestMapping("/get")
//    查询非药品信息
    public JSONObject getNonMedicine(@RequestBody JSONObject non_medicine_id){
        String getString = non_medicine_id.getString("non_medicine_id");
        return ConstantUtils.responseSuccess(nonMedicineService.getNonMedicine(getString));
    }

    @RequestMapping("/add")
    public JSONObject addNonMedicine(@RequestBody NonMedicine nonMedicine){
        if(nonMedicineService.addNonMedicine(nonMedicine)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update")
    public JSONObject updateNonMedicine(@RequestBody NonMedicine nonMedicine){
        if(nonMedicineService.updateNonMedicine(nonMedicine)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/delete")
    public JSONObject deleteNonMedicine(@RequestBody JSONObject non_medicine_id){
        String getString = non_medicine_id.getString("non_medicine_id");
        if(nonMedicineService.deleteNonMedicine(getString)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }
}
