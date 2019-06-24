package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.po.*;
import com.neuedu.lab.model.service.DoctorService;
import com.neuedu.lab.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 给医生的接口
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/get-all-registers")
    public JSONObject getAllRegisters(@RequestBody JSONObject request){
        return doctorService.getAllRegisters(request.getInteger("doctor_id"));
    }

    @RequestMapping("/get-doctor-treated-registers")
    public JSONObject getTreatedRegisters(@RequestBody JSONObject request){
        return doctorService.getTreatedRegisters(request.getInteger("doctor_id"));
    }

    @RequestMapping("/get-department-registers")
    public JSONObject getDepartmentRegisters(@RequestBody JSONObject request){
        return doctorService.getAllDepartmentRegisters(request.getString("department_id"));
    }

    //接诊
    @RequestMapping("/treat")
    public JSONObject treat( @RequestBody JSONObject request){
        //接诊病人
        return doctorService.treat(request.getInteger("register_id"));
    }

    //医生根据患者姓名查询挂号信息
    @RequestMapping("/get-register")
    public JSONObject getRegisterByDoctorIdAndPatientName(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(doctorService.getRegisterByDoctorIdAndPatientName(request.getInteger("doctor_id"),request.getString("patient_name")));
    }

    //提交诊断
    @RequestMapping("/submit-record")
    public JSONObject submitRecord(@RequestBody Record record) {
        return doctorService.submitRecord(record);
    }

    //提交初步诊断
    @RequestMapping("/submit-first-diagnose")
    public JSONObject submitFirstDiagnose(@RequestBody List<Diagnose> diagnoses){
        return doctorService.submitFirstDiagnose(diagnoses);
    }

    //确认初步诊断
    @RequestMapping("/confirm-first-diagnose")
    public JSONObject confirmFirstDiagnose(@RequestBody JSONObject request){
        return doctorService.confirmFirstDiagnose(request.getInteger("record_id"));
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

    @RequestMapping("/check-result")
    public JSONObject checkResult(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(doctorService.checkResult(request.getInteger("medical_skill_id")));
    }

    //查看初步诊断
    @RequestMapping("/get-record")
    public JSONObject getRecord(@RequestBody JSONObject request){
        return doctorService.getRecord(request.getInteger("record_id"));
    }

    //提交最终诊断
    @RequestMapping("/submit-final-diagnose")
    public JSONObject submitFinalDiagnose(@RequestBody List<Diagnose> diagnoses){
        return doctorService.submitFinalDiagnose(diagnoses);
    }

    //新增处方
    @RequestMapping("/add-prescription")
    public JSONObject addPrescription(@RequestBody Prescription prescription){
        return doctorService.addPrescription(prescription);
    }

    //删除处方
    @RequestMapping("/delete-prescription")
    public JSONObject deletePrescription(@RequestBody JSONObject request){
        return doctorService.deletePrescription(request.getInteger("prescription_id"));
    }

    //发送处方、作废处方
        @RequestMapping("/send-prescription")
    public JSONObject sendPrescription(@RequestBody JSONObject request){
       return doctorService.sendPrescription(request.getInteger("prescription_id"));
    }
    @RequestMapping("/end-prescription")
    public JSONObject endPrescription(@RequestBody JSONObject request){
        return doctorService.endPrescription(request.getInteger("prescription_id"));
    }



    //增加药品
    @RequestMapping("/add-medicine")
    public JSONObject addMedicine(@RequestBody PrescriptionContent prescriptionContent){
        return doctorService.addPrescriptionContent(prescriptionContent);
    }

    //删除药品
    @RequestMapping("/delete-medicine")
    public JSONObject deleteMedicine(@RequestBody JSONObject request){
        return doctorService.deletePrescriptionContent(request.getInteger("prescription_content_id"));
    }


    //诊毕
    @RequestMapping("/finish")
    public JSONObject finish(@RequestBody JSONObject request){
        return doctorService.finish(request.getInteger("register_id"));
    }

    //查询患者本次看病费用记录
    @RequestMapping("/get-fee-records")
    public JSONObject fee(@RequestBody JSONObject request){
        return doctorService.getFeeRecords(request.getInteger("register_id"));
    }

    //获取病人的所有处方
    @RequestMapping("/get-all-prescription")
    public JSONObject getPrescription(@RequestBody JSONObject request){
        return doctorService.getPrescription(request.getInteger("register_id"));
    }

    //获取病人的所有医技
    @RequestMapping("/get-all-medical-skill")
    public JSONObject getMedicalSkill(@RequestBody JSONObject request){
        return doctorService.getMedicalSkill(request.getInteger("register_id"));
    }
}
