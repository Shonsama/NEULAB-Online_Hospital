package com.neuedu.lab.model.service;

import com.neuedu.lab.Constant;
import com.neuedu.lab.model.mapper.ConstantMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConstantService {
    @Resource
    private ConstantMapper constantMapper;


    public List<String> getPaymentType(){
        return constantMapper.getPaymentType(Constant.CONSTANT_PAYMENT_TYPE);
    }

}
