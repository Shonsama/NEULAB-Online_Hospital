package com.neuedu.lab.model.mapper;


import com.neuedu.lab.model.po.Doctor;


import java.util.List;

public interface DoctorMapper {
    public List<Doctor> getAllDoctors(String id);

    public void treat(Integer register_id,String state);
}
