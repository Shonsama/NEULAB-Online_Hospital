package com.neuedu.lab.model.mapper;


import com.neuedu.lab.model.po.Doctor;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface DoctorMapper {
    public List<Doctor> getAllDoctorsByDepartment(@Param("department_id") String department_id, @Param("doctor_register_level_id") Integer doctor_register_level_id);

    public void treat(@Param("register_id") Integer register_id,@Param("state") String state);

    Doctor getDoctorById(@Param("doctor_id") Integer doctor_id);

    //更新医生号源数量
    void updateDoctorRegisterNum(@Param("doctor_id") Integer doctor_id,
                                 @Param("num") Integer num);
}
