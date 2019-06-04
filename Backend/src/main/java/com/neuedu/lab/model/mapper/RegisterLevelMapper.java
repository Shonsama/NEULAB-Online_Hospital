package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.RegisterLevel;

import java.util.List;

public interface RegisterLevelMapper {
    public List<RegisterLevel> getAllRegisterLevels();
    public void addRegisterLevel(RegisterLevel registerLevel);
    public void deleteRegisterLevel(Integer register_level_id);
    public void updateRegisterLevel(RegisterLevel registerLevel);
    public RegisterLevel getRegisterLevel(Integer register_level_id);
}
