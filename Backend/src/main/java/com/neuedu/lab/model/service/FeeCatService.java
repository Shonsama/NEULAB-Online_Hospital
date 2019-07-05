package com.neuedu.lab.model.service;

import com.neuedu.lab.model.mapper.FeeCatMapper;
import com.neuedu.lab.model.po.FeeCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FeeCatService {
    @Resource
    private FeeCatMapper feeCatMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public FeeCat getFeeCat(String fee_cat_id){
        FeeCat feeCat = new FeeCat();
        String fee_cat_name = redisTemplate.opsForHash().get("fee_cat:hash",fee_cat_id).toString();
        feeCat.setFee_cat_id(fee_cat_id);
        feeCat.setFee_cat_name(fee_cat_name);
        return feeCat;
        //return feeCatMapper.getFeeCat(fee_cat_id);
    }

    public boolean addFeeCat(FeeCat feeCat){
        //add to Redis
        redisTemplate.opsForHash().put("fee_cat:hash", feeCat.getFee_cat_id(), feeCat.getFee_cat_name());
        //modify mysql database
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
        redisTemplate.opsForHash().delete("fee_cat:hash", fee_cat_id);
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
        redisTemplate.opsForHash().delete("fee_cat:hash", feeCat.getFee_cat_id());
        redisTemplate.opsForHash().put("fee_cat:hash", feeCat.getFee_cat_id(), feeCat.getFee_cat_name());
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
        //List<FeeCat> feeCatList = new ArrayList<>();
        //Map<Object, Object> entries = redisTemplate.opsForHash().entries("fee_cat:hash");
    }
}
