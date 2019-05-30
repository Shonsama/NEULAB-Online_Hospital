package com.neuedu.lab.controller;

import com.neuedu.lab.model.po.*;
import com.neuedu.lab.model.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/get-all-registers")
    public List<Register> getAllRegisters(@RequestParam Integer doctor_id){
        return doctorService.getAllRegisters(doctor_id);
    }


    //接诊
    @RequestMapping("/treat")
    public String treat( @RequestParam Integer register_id){
        //接诊病人
        if(doctorService.treat(register_id)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    //医生根据患者姓名查询挂号信息
    @RequestMapping("/get-register")
    public List<Register> getRegisterByDoctorIdAndPatientName(@RequestParam Integer doctor_id, @RequestParam String patient_name){
        return doctorService.getRegisterByDoctorIdAndPatientName(doctor_id,patient_name);
    }

    //提交诊断
    @RequestMapping("/submit-record")
    public String submitRecord(@RequestParam Record record){
        if(doctorService.submitRecord(record)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    //添加医技项目
    @RequestMapping("/add-medical-skill")
    public String addNonMedicine(@RequestParam MedicalSkill medicalSkill){
        if(doctorService.addMedicialSkill(medicalSkill)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }

    }

    //删除医技项目
    @RequestMapping("/delete-medical-skill")
    public String deleteNonMedicine(@RequestParam Integer medical_skill_id){
        if(doctorService.deleteMedicialSkill(medical_skill_id)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }

    }

    //开立医技项目
    @RequestMapping("/start-medical-skill")
    public String startNonMedicine(@RequestParam Integer medical_skill_id){
        if(doctorService.startMedicialSkill(medical_skill_id)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }

    }

    //作废医技项目
    @RequestMapping("/end-medical-skill")
    public String endNonMedicine(@RequestParam Integer medical_skill_id){
        if(doctorService.endMedicialSkill(medical_skill_id)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    //查看初步诊断
    @RequestMapping("/get-record")
    public Record getRecord(@RequestParam Integer record_id){
        return doctorService.getRecord(record_id);
    }

    //提交最终诊断
    @RequestMapping("/submit-final-diagnose")
    public String submitFinalDiagnose(@RequestParam List<Diagnose> diagnoses){
        if(doctorService.submitFinalDiagnose(diagnoses)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }








}
