package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Patient;
import com.neuedu.lab.model.po.Prescription;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PrescriptionMapper {

    void addPrescription(Prescription prescription);

    void deletePrescription(Integer prescription_id);

    void updatePrescriptionState(@Param("prescription_id") Integer prescription_id,@Param("prescription_execute_state") String prescription_execute_state);

    Prescription getPrescription(Integer prescription_id);

    List<Prescription> getPrescriptionByRegisterId(Integer prescription_register_info_id);

    //退费部分使用
    void updatePrescriptionFee(Prescription prescription);

    //供更新姓名使用
    void updatePrescriptionName(Prescription prescription);

    List<Prescription> getPrescriptionsInPayCondition(Integer prescription_register_info_id);
    List<Prescription> getPrescriptionsNotInPayCondition(Integer prescription_register_info_id);

    //通过patient_id找到所有已经缴费prescription
    List<Prescription> getPrescriptionByPatientId(@Param("register_info_patient_id") Integer register_info_patient_id,
                                                  @Param("prescription_execute_state") String prescription_execute_state,
                                                  @Param("start_time") Date start_time,
                                                  @Param("end_time" )Date end_time);

    List<Patient> getAllPatients(String prescription_execute_state);
}
