package com.neuedu.lab.controller;

import com.neuedu.lab.model.po.Medicine;
import com.neuedu.lab.model.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @RequestMapping("/getall")
//    返回所有药品信息
    public List<Medicine> getAllMedicines(){
        return medicineService.getAllMedicines();
    }

    @RequestMapping("/get")
//    查询药品信息
    public List<Medicine> getMedicine(@RequestParam String medicine_id){
        return medicineService.getMedicine(medicine_id);
    }

    @RequestMapping("/add")
    public String addMedicine(@RequestParam Medicine medicine){
        if(medicineService.addMedicine(medicine)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/update")
    public String updateMedicine(@RequestParam Medicine medicine){
        if(medicineService.updateMedicine(medicine)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/delete")
    public String deleteMedicine(@RequestParam Medicine medicine){
        if(medicineService.deleteMedicine(medicine)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }
}
