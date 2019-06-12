package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.Patient;
import com.neuedu.lab.model.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @RequestMapping("/get-all")
    public JSONObject getAllPatients(){
        return ConstantUtils.responseSuccess(patientService.getAllPatients());
    }

    @RequestMapping("/add")
    public JSONObject addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }

    @RequestMapping("/get-by-patient-id")
    public JSONObject getPatientByRecordId(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(patientService.getPatientByRecordId(request.getInteger("patient_record_id")));
    }

    @RequestMapping("/get-by-doctor-id")
    public JSONObject getPatientByDoctorId(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(patientService.getPatientByDoctorId(request.getString("register_info_doctor_id")));
    }

    @RequestMapping("/get-by-department-id")
    public JSONObject getPatientByDepartmentId(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(patientService.getPatientByDepartmentId(request.getString("doctor_department_id")));
    }

    @RequestMapping("/sign-up")
    public JSONObject signUp(@RequestBody JSONObject request){
        return patientService.signUp(request.getString("patient_account"),request.getString("patient_password"));
    }
    @RequestMapping("/bound-exist-record")
    public JSONObject boundExistedRecord(@RequestBody JSONObject request){
        return patientService.boundExistedRecord(request.getString("patient_account"),request.getInteger("patient_record_id"));
    }
    @RequestMapping("/create-new-record")
    public JSONObject createNewRecord(@RequestBody JSONObject request){
        //PatientUser patientUser = new PatientUser();
        Patient patient = new Patient();
        //patientUser.setPatient_account(request.getString("patient_account"));
        patient.setPatient_gender(request.getBoolean("patient_gender"));
        patient.setPatient_name(request.getString("patient_name"));
        patient.setPatient_credit_id(request.getString("patient_credit_id"));
        patient.setPatient_address(request.getString("patient_address"));
        patient.setPatient_birthDate(request.getDate("patient_birthDate"));
        patient.setPatient_age(request.getInteger("patient_age"));
        return patientService.createNewRecord(patient,request.getString("patient_account"));
    }

    @RequestMapping("/login")
    public JSONObject checkUserValid(@RequestBody JSONObject request){
        return patientService.checkUserValid(request.getString("patient_account"),request.getString("patient_password"));
    }
}
