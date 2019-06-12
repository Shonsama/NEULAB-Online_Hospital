package com.neuedu.lab.controller;


import com.neuedu.lab.model.mapper.DoctorMapper;
import com.neuedu.lab.model.mapper.RegisterMapper;
import com.neuedu.lab.model.po.Register;

import javax.annotation.Resource;

public class VaildService {
    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private RegisterMapper registerMapper;

    public boolean isRegisterStateRegistered(Integer register_id,String REGISTER_STATE){ //验证挂号状态为“已挂号”
        Register register;
        try{
           register = registerMapper.getRegister(register_id);
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
        return register.getRegister_info_state().equals(REGISTER_STATE);
    }

}
