package com.neuedu.lab.model.service;

import org.springframework.stereotype.Service;


import static com.neuedu.lab.utils.ConstantUtils.logger;

@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    public void executeAsync() {
        logger.info("=========================start executeAsync");
        try{
            Thread.sleep(5000);
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("==========================end executeAsync");
    }
}
