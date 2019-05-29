package com.neuedu.lab.controller;

import com.neuedu.lab.model.po.Patient;
import com.neuedu.lab.model.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

//    @RequestMapping("/get-all-patients")
//    public List<Patient>

}
