package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.PatientMapper;
import com.neuedu.lab.model.po.Patient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PatientService {

    @Resource
    private PatientMapper patientMapper;

    public List<Patient> getAllPatients(){
        return patientMapper.getAllPatients();
    }

    public JSONObject addPatient(Patient patient){
        try {
            patientMapper.addPatient(patient);
        }
        catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail();
        }
        return ConstantUtils.responseSuccess(patient);
    }

    public Patient getPatientByRecordId(Integer patient_id){
        return patientMapper.getPatientByRecordId(patient_id);
    }

    public List<Patient> getPatientByDoctorId(String doctor_id){
        return patientMapper.getPatientByDoctorId(doctor_id);
    }

    public List<Patient> getPatientByDepartmentId(String department_id){
        return patientMapper.getPatientByDepartmentId(department_id);
    }
}
