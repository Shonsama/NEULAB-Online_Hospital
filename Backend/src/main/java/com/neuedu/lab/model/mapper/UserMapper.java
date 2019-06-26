package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Doctor;
import com.neuedu.lab.model.po.User;

import java.util.List;

public interface UserMapper {

    public User getUser(String user_id);
    public User getUserByAccount(String user_account);
    public List<User> getAllUsers();
    List<String> getAllUsersAccount();
    public void addUser(User user);
    public void deleteUser(String user_account);
    public void updateUser(User user);
    public void updateUserPassword(User user);


    public Doctor getDoctor(String doctor_id);
    public Doctor getDoctorByAccount(String doctor_account);
    public List<Doctor> getAllDoctors();
    List<String> getAllDoctorsAccount();
    public void addDoctor(Doctor doctor);
    public void deleteDoctor(String doctor_account);
    public void updateDoctor(Doctor doctor);
    public void updateDoctorPassword(Doctor doctor);
    public List<User> getAllCashiers(String user_type);
}
