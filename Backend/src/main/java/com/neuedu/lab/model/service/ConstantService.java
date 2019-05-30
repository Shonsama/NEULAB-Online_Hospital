package com.neuedu.lab.model.service;

import com.neuedu.lab.ConstantDefinition;
import com.neuedu.lab.model.po.Constant;
import com.neuedu.lab.model.mapper.ConstantMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConstantService {
    @Resource
    private ConstantMapper constantMapper;


    public List<Constant> getConstant(String constant_type){
        return constantMapper.getConstant(constant_type);
    }

    public boolean addConstant(Constant constant){
        try {
            constantMapper.addConstant(constant);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteConstant(Constant constant){
        try {
            constantMapper.deleteConstant(constant);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateConstant(Constant constant){
        try {
            constantMapper.updateConstant(constant);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public List<Constant> getAllConstant() {
        try{
            return constantMapper.getAllConstant();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
