package com.neuedu.lab.model.mapper;


import com.neuedu.lab.model.po.Constant;

import java.util.List;

public interface ConstantMapper {
    public List<Constant> getConstant(String constant_type);
    public void addConstant(Constant constant);
    public void deleteConstant(Constant constant);
    public void updateConstant(Constant constant);

    List<Constant> getAllConstant();
}
