package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Register;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegisterMapper {

    public void addRegister(Register register);

    public Register getRegister(Integer register_id);

    public List<Register> getRegistersByPatientId(Integer register_info_patient_id);

    public List<Register> getRegisterByDoctorId(Integer doctor_id);

    public List<Register> getRegisterByDepartmentId(String department_id);

    public List<Register> getRegisterByDoctorIdAndPatientName(Integer doctor_id, String patient_name);

    public void updateRegisterState(@Param("register_id") Integer register_id, @Param("register_info_state") String register_info_state);


    //校验方法
    //校验同一患者是否在同一天挂了同一医生的号
    List<Register> checkVaild (Register register);
}
