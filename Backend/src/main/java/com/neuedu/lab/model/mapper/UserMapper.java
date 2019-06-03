package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.User;

import java.util.List;

public interface UserMapper {

    public List<User> getUser(String user_account);
    public List<User> getAllUsers();
    public void addUser(User user);
    public void deleteUser(User user);
    public void updateUser(User user);
    public void updateUserPassword(User user);
}
