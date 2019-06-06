package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Patient;

import java.util.List;

public interface PatientMapper {
    public void addPatient(Patient patient);
    public List<Patient> getAllPatients();
    public Patient getPatientByRecordId(Integer patient_record_id);
    public List<Patient> getPatientByDoctorId(String register_info_doctor_id);
    public List<Patient> getPatientByDepartmentId(String doctor_department_id);
}
