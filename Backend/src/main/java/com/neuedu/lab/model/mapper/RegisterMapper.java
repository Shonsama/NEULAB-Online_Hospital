package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Register;

public interface RegisterMapper {

    public void addRegister(Register register);

    public Register getRegister(Integer register_id);
}
