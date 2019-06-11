package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Department;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface DepartmentWorkloadMapper {
    Integer getVisitorsNum(@Param("start_time") Date start_time, @Param("end_time")Date end_time, @Param("department_id")String department_id);

    Integer getBillsNum(@Param("start_time")Date start_time,@Param("end_time")Date end_time, @Param("department_id")String department_id);

    BigDecimal getRegisterFee(@Param("start_time")Date start_time, @Param("end_time")Date end_time, @Param("department_id")String department_id);

    BigDecimal getMedicalSkillInspect(@Param("start_time")Date start_time,@Param("end_time")Date end_time, @Param("department_id")String department_id);

    BigDecimal getMedicalSkillCheck(@Param("start_time")Date start_time,@Param("end_time")Date end_time, @Param("department_id")String department_id);

    BigDecimal getMedicalSkillDispose(@Param("start_time")Date start_time,@Param("end_time")Date end_time, @Param("department_id")String department_id);

    BigDecimal getPrescriptionWest(@Param("start_time")Date start_time,@Param("end_time")Date end_time, @Param("department_id")String department_id);

    BigDecimal getPrescriptionChinese(@Param("start_time")Date start_time,@Param("end_time")Date end_time, @Param("department_id")String department_id);

    List<Department> getDepartmentsClinical();

    List<Department> getDepartmentsMedicalSkill();
}
