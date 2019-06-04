package com.neuedu.lab.model.service;

import com.neuedu.lab.model.mapper.DiseaseInfoMapper;
import com.neuedu.lab.model.po.DiseaseInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DiseaseInfoService {
    @Resource
    private DiseaseInfoMapper diseaseInfoMapper;

    public List<DiseaseInfo> getAllDiseases(){
        return diseaseInfoMapper.getAllDiseases();
    }

    public DiseaseInfo getDisease(String disease_id){
        return diseaseInfoMapper.getDisease(disease_id);
    }

    public boolean addDisease(DiseaseInfo diseaseInfo){
        try {
            diseaseInfoMapper.addDisease(diseaseInfo);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteDisease(String disease_id){
        try {
            diseaseInfoMapper.deleteDisease(disease_id);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateDisease(DiseaseInfo diseaseInfo){
        try {
            diseaseInfoMapper.updateDisease(diseaseInfo);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
