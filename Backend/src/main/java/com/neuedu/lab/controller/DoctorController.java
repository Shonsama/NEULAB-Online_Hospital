package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.po.*;
import com.neuedu.lab.model.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/get-all-registers")
    public List<Register> getAllRegisters(@RequestBody JSONObject doctor_id){
        Integer getInt = doctor_id.getInteger("doctor_id");
        return doctorService.getAllRegisters(getInt);
    }

    //接诊
    @RequestMapping("/treat")
    public String treat( @RequestBody JSONObject register_id){
        //接诊病人
        Integer getInt = register_id.getInteger("register_id");
        if(doctorService.treat(getInt)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    //医生根据患者姓名查询挂号信息
    @RequestMapping("/get-register")
    public List<Register> getRegisterByDoctorIdAndPatientName(@RequestBody JSONObject doctor_id, @RequestBody JSONObject patient_name){
        Integer getInt = doctor_id.getInteger("doctor_id");
        String getString = patient_name.getString("patient_name");
        return doctorService.getRegisterByDoctorIdAndPatientName(getInt,getString);
    }

    //提交诊断
    @RequestMapping("/submit-record")
    public String submitRecord(@RequestBody Record record){
        if(doctorService.submitRecord(record)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    //添加医技项目
    @RequestMapping("/add-medical-skill")
    public String addMedicalSkill(@RequestBody MedicalSkill medicalSkill){
        if(doctorService.addMedicalSkill(medicalSkill)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }

    }

    //删除医技项目
    @RequestMapping("/delete-medical-skill")
    public String deleteMedicalSkill(@RequestBody JSONObject medical_skill_id){
        Integer getInt = medical_skill_id.getInteger("medical_skill_id");
        if(doctorService.deleteMedicalSkill(getInt)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }

    }

    //开立医技项目
    @RequestMapping("/start-medical-skill")
    public String startMedicalSkill(@RequestBody JSONObject medical_skill_id){
        Integer getInt =medical_skill_id.getInteger("medical_skill_id");
        if(doctorService.startMedicalSkill(getInt)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }

    }

    //作废医技项目
    @RequestMapping("/end-medical-skill")
    public String endMedicalSkill(@RequestBody JSONObject medical_skill_id){
        Integer getInt =medical_skill_id.getInteger("medical_skill_id");
        if(doctorService.endMedicalSkill(getInt)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    //查看初步诊断
    @RequestMapping("/get-record")
    public Record getRecord(@RequestBody JSONObject record_id){
        Integer getInt =record_id.getInteger("record_id");
        return doctorService.getRecord(getInt);
    }

    //提交最终诊断
    @RequestMapping("/submit-final-diagnose")
    public String submitFinalDiagnose(@RequestBody List<Diagnose> diagnoses){
        if(doctorService.submitFinalDiagnose(diagnoses)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    //新增处方
    @RequestMapping("/add-prescription")
    public String addPrescription(@RequestBody Prescription prescription){
        if(doctorService.addPrescription(prescription)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    //删除处方
    @RequestMapping("/delete-prescription")
    public String deletePrescription(@RequestBody JSONObject prescription_id){
        Integer getInt = prescription_id.getInteger("prescription_id");
        if(doctorService.deletePrescription(getInt)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    //发送处方、作废处方
    @RequestMapping("/update-prescription")
    public String updatePrescription(@RequestBody JSONObject prescription_id,
                                     @RequestBody JSONObject prescription_execute_state){
        Integer getInt = prescription_id.getInteger("prescription_id");
        String getString = prescription_execute_state.getString("prescription_execute_state");
        if(doctorService.updatePrescription(getInt,getString)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }


    //增加药品
    @RequestMapping("/add-medicine")
    public String addMedicine(@RequestBody PrescriptionContent prescriptionContent){
        if(doctorService.addPrescriptionContent(prescriptionContent)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    //删除药品
    @RequestMapping("/delete-medicine")
    public String deleteMedicine(@RequestBody JSONObject prescription_id){
        Integer getInt = prescription_id.getInteger("prescription_id");
        if(doctorService.deletePrescriptionContent(getInt)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }


    //诊毕
    @RequestMapping("/finsh")
    public String finish(@RequestBody JSONObject register_id){
        Integer getInt = register_id.getInteger("register_id");
        if(doctorService.finish(getInt)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

}
