package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Patient;
import com.neuedu.lab.model.po.PatientUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PatientMapper {
    public void addPatient(Patient patient);
    public List<Patient> getAllPatients();
    public Patient getPatientByRecordId(Integer patient_record_id);
    public List<Patient> getPatientByDoctorId(String register_info_doctor_id);
    public List<Patient> getPatientByDepartmentId(String doctor_department_id);

    void addPatientUser(PatientUser patientUser);
    void deletePatientUser(Integer patient_user_id);
    void updatePatientUser(PatientUser patientUser);
    PatientUser getPatientUserById(Integer patient_user_id);
    PatientUser getPatientUserByAccount(String patient_account);

    List<String> getAllAccounts();
    List<Integer> getAllPatientRecordIds();
    void updatePatientUserRecordId(@Param("patient_connect_id")Integer patient_connect_id, @Param("patient_account")String patient_account);
}
