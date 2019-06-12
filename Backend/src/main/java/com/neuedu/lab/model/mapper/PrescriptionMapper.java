package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Patient;
import com.neuedu.lab.model.po.Prescription;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PrescriptionMapper {

    void addPrescription(Prescription prescription);

    void deletePrescription(Integer prescription_id);

    void updatePrescriptionState(@Param("prescription_id") Integer prescription_id,
                                 @Param("prescription_execute_state") String prescription_execute_state);

    void updatePrescriptionPaytime(@Param("prescription_id") Integer prescription_id,
                                 @Param("prescription_pay_time") Date prescription_pay_time,
                                   @Param("prescription_user_id") Integer medical_skill_user_id);

    Prescription getPrescription(Integer prescription_id);

    List<Prescription> getPrescriptionByRegisterId(Integer prescription_register_info_id);

    //退费部分使用
    void updatePrescriptionFee(Prescription prescription);

    //供更新姓名使用
    void updatePrescriptionName(Prescription prescription);

    List<Prescription> getPrescriptionsInReturnedOrPaid(Integer register_info_patient_id);
    List<Prescription> getPrescriptionsInSent(Integer register_info_patient_id);

    //通过patient_id找到所有已经缴费prescription
    List<Prescription> getPrescriptionByPatientIdBySentTime(@Param("register_info_patient_id") Integer register_info_patient_id,
                                                  @Param("prescription_execute_state") String prescription_execute_state,
                                                  @Param("start_time") Date start_time,
                                                  @Param("end_time" )Date end_time);
    List<Prescription> getPrescriptionByPatientIdByPayTime(@Param("register_info_patient_id") Integer register_info_patient_id,
                                                            @Param("prescription_execute_state") String prescription_execute_state,
                                                            @Param("start_time") Date start_time,
                                                            @Param("end_time" )Date end_time);

    List<Patient> getAllPatients(String prescription_execute_state);

    //药房发药更改处方发药时间
    void updatePrescription(Prescription prescription);
}
