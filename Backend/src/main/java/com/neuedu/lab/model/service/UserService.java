package com.neuedu.lab.model.service;

import com.neuedu.lab.model.mapper.UserMapper;
import com.neuedu.lab.model.po.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    /*获取所有用户信息*/
    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }

    public List<User> getUser(String user_account){
        return userMapper.getUser(user_account);
    }

    public boolean checkIfExist(String user_account){
        if(userMapper.getUser(user_account) == null){//没有该用户
            return false;
        }else
            return true;
    }

    /*检验登录*/
    public String checkValid(String user_account, String user_password){
        if(checkIfExist(user_account) == false){//没有该用户
            return "noUser";
        }else{
            User user = userMapper.getUser(user_account).get(0);
            if(user.getUser_password() == user_password){
                return "success";
            }
            else{
                return "wrongPassword";
            }
        }
    }

    public boolean addUser(User user){
        String user_account = user.getUser_account();
        if(checkIfExist(user_account) == false){
            userMapper.addUser(user);
            return true;
        }
        else
            return false;//存在该用户
    }

    public boolean deleteUser(User user){
        String user_account = user.getUser_account();
        if(checkIfExist(user_account) == false){
            userMapper.deleteUser(user);
            return true;
        }else
            return false;
    }

    public boolean updateUser(User user){
        String user_account = user.getUser_account();
        if(checkIfExist(user_account) == false){
            userMapper.updateUser(user);
            return true;
        }else
            return false;
    }

    public boolean updateUserPassword(User user){
        String user_account = user.getUser_account();
        if(checkIfExist(user_account) == false){
            userMapper.updateUserPassword(user);
            return true;
        }else
            return false;
    }
}
