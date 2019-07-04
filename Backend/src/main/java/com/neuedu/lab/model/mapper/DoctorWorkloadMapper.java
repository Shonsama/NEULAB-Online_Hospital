package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Doctor;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface DoctorWorkloadMapper {
    Integer getVisitorsNum(@Param("start_time") Date start_time, @Param("end_time") Date end_time, @Param("doctor_id") Integer doctor_id);

    Integer getBillsNum(@Param("start_time") Date start_time, @Param("end_time") Date end_time, @Param("doctor_id") Integer doctor_id);
    Integer getBillsNumInRegister(@Param("start_time") Date start_time, @Param("end_time") Date end_time, @Param("doctor_id") Integer doctor_id);
    Integer getBillsNumInMedicalSkill(@Param("start_time") Date start_time, @Param("end_time") Date end_time, @Param("doctor_id") Integer doctor_id);
    Integer getBillsNumInPrescription(@Param("start_time") Date start_time, @Param("end_time") Date end_time, @Param("doctor_id") Integer doctor_id);

    BigDecimal getRegisterFee(@Param("start_time") Date start_time, @Param("end_time") Date end_time, @Param("doctor_id") Integer doctor_id);

    BigDecimal getMedicalSkillInspect(@Param("start_time") Date start_time, @Param("end_time") Date end_time, @Param("doctor_id") Integer doctor_id);

    BigDecimal getMedicalSkillCheck(@Param("start_time") Date start_time, @Param("end_time") Date end_time, @Param("doctor_id") Integer doctor_id);

    BigDecimal getMedicalSkillDispose(@Param("start_time") Date start_time, @Param("end_time") Date end_time, @Param("doctor_id") Integer doctor_id);

    BigDecimal getPrescriptionWest(@Param("start_time") Date start_time, @Param("end_time") Date end_time, @Param("doctor_id") Integer doctor_id);

    BigDecimal getPrescriptionChinese(@Param("start_time") Date start_time, @Param("end_time") Date end_time, @Param("doctor_id") Integer doctor_id);

    List<Doctor> getDoctors();
}
