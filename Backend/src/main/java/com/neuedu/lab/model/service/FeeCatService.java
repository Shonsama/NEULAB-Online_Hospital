package com.neuedu.lab.model.service;

import com.neuedu.lab.model.mapper.FeeCatMapper;
import com.neuedu.lab.model.po.FeeCat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FeeCatService {
    @Resource
    private FeeCatMapper feeCatMapper;

    public FeeCat getFeeCat(String fee_cat_id){
        return feeCatMapper.getFeeCat(fee_cat_id);
    }

    public boolean addFeeCat(FeeCat feeCat){
        try {
            feeCatMapper.addFeeCat(feeCat);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteFeeCat(String fee_cat_id){
        try {
            feeCatMapper.deleteFeeCat(fee_cat_id);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateFeeCat(FeeCat feeCat){
        try {
            feeCatMapper.updateFeeCat(feeCat);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<FeeCat> getAllFeeCats() {
        try{
            return feeCatMapper.getAllFeeCats();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
