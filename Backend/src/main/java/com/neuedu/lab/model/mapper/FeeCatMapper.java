package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.FeeCat;

import java.util.List;

public interface FeeCatMapper {
    public FeeCat getFeeCat(String fee_cat_id);
    public void addFeeCat(FeeCat feeCat);
    public void deleteFeeCat(String fee_cat_id);
    public void updateFeeCat(FeeCat feeCat);

    List<FeeCat> getAllFeeCats();
}
