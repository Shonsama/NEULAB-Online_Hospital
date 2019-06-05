package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
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
    public JSONObject getAllRegisters(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess( doctorService.getAllRegisters(request.getInteger("doctor_id")));
    }

    @RequestMapping("/get-department-registers")
    public JSONObject getDepartmentRegisters(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess( doctorService.getAllDepartmentRegisters(request.getInteger("department_id")));
    }

    //接诊
    @RequestMapping("/treat")
    public JSONObject treat( @RequestBody JSONObject request){
        //接诊病人
        if(doctorService.treat(request.getInteger("register_id"))){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //医生根据患者姓名查询挂号信息
    @RequestMapping("/get-register")
    public JSONObject getRegisterByDoctorIdAndPatientName(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(doctorService.getRegisterByDoctorIdAndPatientName(request.getInteger("doctor_id"),request.getString("patient_name")));
    }

    //提交诊断
    @RequestMapping("/submit-record")
    public JSONObject submitRecord(@RequestBody Record record){
        if(doctorService.submitRecord(record)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //添加医技项目
    @RequestMapping("/add-medical-skill")
    public JSONObject addMedicalSkill(@RequestBody MedicalSkill medicalSkill){
        if(doctorService.addMedicalSkill(medicalSkill)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //删除医技项目
    @RequestMapping("/delete-medical-skill")
    public JSONObject deleteMedicalSkill(@RequestBody JSONObject medical_skill_id){
        Integer getInt = medical_skill_id.getInteger("medical_skill_id");
        if(doctorService.deleteMedicalSkill(getInt)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }

    }

    //开立医技项目
    @RequestMapping("/start-medical-skill")
    public JSONObject startMedicalSkill(@RequestBody JSONObject request){
        if(doctorService.startMedicalSkill(request.getInteger("medical_skill_id"))){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }

    }

    //作废医技项目
    @RequestMapping("/end-medical-skill")
    public JSONObject endMedicalSkill(@RequestBody JSONObject request){
        if(doctorService.endMedicalSkill(request.getInteger("medical_skill_id"))){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //查看初步诊断
    @RequestMapping("/get-record")
    public Record getRecord(@RequestBody JSONObject request){
        return doctorService.getRecord(request.getInteger("record_id"));
    }

    //提交最终诊断
    @RequestMapping("/submit-final-diagnose")
    public JSONObject submitFinalDiagnose(@RequestBody List<Diagnose> diagnoses){
        if(doctorService.submitFinalDiagnose(diagnoses)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //新增处方
    @RequestMapping("/add-prescription")
    public JSONObject addPrescription(@RequestBody Prescription prescription){
        if(doctorService.addPrescription(prescription)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //删除处方
    @RequestMapping("/delete-prescription")
    public JSONObject deletePrescription(@RequestBody JSONObject prescription_id){
        Integer getInt = prescription_id.getInteger("prescription_id");
        if(doctorService.deletePrescription(getInt)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //发送处方、作废处方
        @RequestMapping("/send-prescription")
    public JSONObject sendPrescription(@RequestBody JSONObject request){
       if(doctorService.sendPrescription(request.getInteger("prescriptioin_id"))){
           return ConstantUtils.responseSuccess(null);
       }
       else {
           return ConstantUtils.responseFail(null);
       }
    }
    @RequestMapping("/end-prescription")
    public JSONObject endPrescription(@RequestBody JSONObject request){
        if(doctorService.endPrescription(request.getInteger("prescriptioin_id"))){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }



    //增加药品
    @RequestMapping("/add-medicine")
    public JSONObject addMedicine(@RequestBody PrescriptionContent prescriptionContent){
        if(doctorService.addPrescriptionContent(prescriptionContent)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //删除药品
    @RequestMapping("/delete-medicine")
    public JSONObject deleteMedicine(@RequestBody JSONObject request){
        if(doctorService.deletePrescriptionContent(request.getInteger("prescription_content_id"))){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }


    //诊毕
    @RequestMapping("/finish")
    public JSONObject finish(@RequestBody JSONObject request){
        if(doctorService.finish(request.getInteger("register_id"))){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //查询患者本次看病
    @RequestMapping("/get-fee-records")
    public JSONObject fee(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(doctorService.getFeeRecords(request.getInteger("patient_record_id")));
    }

}
