package com.neuedu.lab.model.mapper;


import com.neuedu.lab.model.po.Doctor;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface DoctorMapper {
    public List<Doctor> getAllDoctorsByDepartment(@Param("department_id") String department_id, @Param("doctor_register_level_id") Integer doctor_register_level_id);

    public void treat(Integer register_id,String state);
}
