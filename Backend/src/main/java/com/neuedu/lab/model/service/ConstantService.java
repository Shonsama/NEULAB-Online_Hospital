package com.neuedu.lab.model.service;

import com.neuedu.lab.Constant;
import com.neuedu.lab.model.mapper.ConstantMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ConstantService {
    @Resource
    private ConstantMapper constantMapper;


    public String[] getPaymentType(){
        System.out.println("service getpaymenttype");
        return constantMapper.getPaymentType(Constant.CONSTANT_PAYMENT_TYPE).split(" ");
    }

}
