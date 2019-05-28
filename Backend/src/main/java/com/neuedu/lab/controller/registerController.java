package com.neuedu.lab.controller;

import com.neuedu.lab.model.po.Patient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class registerController {
    @RequestMapping("/get-all-department")
    public String getAllDepartment(){
        return "all department lists are there";
    }

    @RequestMapping("/get-all-doctor")
    public String getAllDoctor(@RequestParam String department_id){
        return "all working doctors in "+department_id+" are there";
    }

    @RequestMapping("/submit")
    public Patient registerSubmit(@RequestParam Patient patient,@RequestParam String doctor_id){
        return patient;
    }




}
