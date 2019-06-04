package com.neuedu.lab.model.service;
import com.neuedu.lab.model.mapper.RegisterLevelMapper;
import com.neuedu.lab.model.po.RegisterLevel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service

public class RegisterLevelService {
    @Resource
    private RegisterLevelMapper registerLevelMapper;
    public List<RegisterLevel> getAllRegisterLevels(){
        return registerLevelMapper.getAllRegisterLevels();
    }

    public RegisterLevel getRegisterLevel(Integer register_level_id){
        return registerLevelMapper.getRegisterLevel(register_level_id);
    }

    public boolean addRegisterLevel(RegisterLevel registerLevel){
        try {
            registerLevelMapper.addRegisterLevel(registerLevel);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteRegisterLevel(Integer register_level_id){
        try {
            registerLevelMapper.deleteRegisterLevel(register_level_id);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateRegisterLevel(RegisterLevel registerLevel){
        try {
            registerLevelMapper.updateRegisterLevel(registerLevel);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
