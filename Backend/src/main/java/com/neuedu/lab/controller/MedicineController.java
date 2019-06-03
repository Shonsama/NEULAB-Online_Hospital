package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.po.Medicine;
import com.neuedu.lab.model.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @RequestMapping("/get-all")
//    返回所有药品信息
    public List<Medicine> getAllMedicines(){
        return medicineService.getAllMedicines();
    }

    @RequestMapping("/get")
//    查询药品信息
    public List<Medicine> getMedicine(@RequestBody JSONObject medicine_id){
        String getString = medicine_id.getString("medicine_id");
        return medicineService.getMedicine(getString);
    }

    @RequestMapping("/add")
    public String addMedicine(@RequestBody Medicine medicine){
        if(medicineService.addMedicine(medicine)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/update")
    public String updateMedicine(@RequestBody Medicine medicine){
        if(medicineService.updateMedicine(medicine)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/delete")
    public String deleteMedicine(@RequestBody JSONObject medicine_id){
        String getString = medicine_id.getString("medicine_id");
        if(medicineService.deleteMedicine(getString)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }
}
