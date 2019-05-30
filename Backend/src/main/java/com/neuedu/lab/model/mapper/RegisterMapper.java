package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Register;

import java.util.List;

public interface RegisterMapper {

    public void addRegister(Register register);

    public Register getRegister(Integer register_id);

    public List<Register> getRegisterByDoctorId(Integer doctor_id);

    public List<Register> getRegisterByDoctorIdAndPatientName(Integer doctor_id, String patient_name);

    public void updateRegisterState(Integer register_id, String register_info_state);


}
