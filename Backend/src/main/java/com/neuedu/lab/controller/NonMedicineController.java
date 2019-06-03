package com.neuedu.lab.controller;


import com.alibaba.fastjson.JSONObject;
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
    public List<NonMedicine> getNonMedicine(@RequestBody JSONObject non_medicine_id){
        String getString = non_medicine_id.getString("non_medicine_id");
        return nonMedicineService.getNonMedicine(getString);
    }

    @RequestMapping("/add")
    public String addNonMedicine(@RequestBody NonMedicine nonMedicine){
        if(nonMedicineService.addNonMedicine(nonMedicine)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/update")
    public String updateNonMedicine(@RequestBody NonMedicine nonMedicine){
        if(nonMedicineService.updateNonMedicine(nonMedicine)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/delete")
    public String deleteNonMedicine(@RequestBody JSONObject non_medicine_id){
        String getString = non_medicine_id.getString("non_medicine_id");
        if(nonMedicineService.deleteNonMedicine(getString)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }
}
