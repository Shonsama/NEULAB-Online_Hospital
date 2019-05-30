package com.neuedu.lab.controller;

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
    public List<Register> getAllRegisters(@RequestBody Integer doctor_id){
        return doctorService.getAllRegisters(doctor_id);
    }


    //接诊
    @RequestMapping("/treat")
    public String treat( @RequestBody Integer register_id){
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
    public List<Register> getRegisterByDoctorIdAndPatientName(@RequestBody Integer doctor_id, @RequestBody String patient_name){
        return doctorService.getRegisterByDoctorIdAndPatientName(doctor_id,patient_name);
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
    public String deleteMedicalSkill(@RequestBody Integer medical_skill_id){
        if(doctorService.deleteMedicalSkill(medical_skill_id)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }

    }

    //开立医技项目
    @RequestMapping("/start-medical-skill")
    public String startMedicalSkill(@RequestBody Integer medical_skill_id){
        if(doctorService.startMedicalSkill(medical_skill_id)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }

    }

    //作废医技项目
    @RequestMapping("/end-medical-skill")
    public String endMedicalSkill(@RequestBody Integer medical_skill_id){
        if(doctorService.endMedicalSkill(medical_skill_id)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    //查看初步诊断
    @RequestMapping("/get-record")
    public Record getRecord(@RequestBody Integer record_id){
        return doctorService.getRecord(record_id);
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
    public String deletePrescription(@RequestBody Integer prescription_id){
        if(doctorService.deletePrescription(prescription_id)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }


    //发送处方、作废处方
    @RequestMapping("/update-prescription")
    public String updatePrescription(@RequestBody Integer prescripiton_id, @RequestBody String prescription_execute_state){
        if(doctorService.updatePrescription(prescripiton_id,prescription_execute_state)){
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
    public String deleteMedicine(@RequestBody Integer prescription_id){
        if(doctorService.deletePrescriptionContent(prescription_id)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }


    //诊毕
    @RequestMapping("/finsh")
    public String finish(@RequestBody Integer register_id){
        if(doctorService.finish(register_id)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

}
