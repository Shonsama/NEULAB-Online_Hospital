package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.DiseaseInfo;

import java.util.List;

public interface DiseaseInfoMapper {
    public List<DiseaseInfo> getAllDiseases();
    public void addDisease(DiseaseInfo diseaseInfo);
    public void deleteDisease(String disease_id);
    public void updateDisease(DiseaseInfo diseaseInfo);
    public List<DiseaseInfo> getDisease(String disease_id);
}
