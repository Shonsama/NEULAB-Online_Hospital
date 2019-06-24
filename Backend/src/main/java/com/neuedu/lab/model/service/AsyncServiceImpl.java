package com.neuedu.lab.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
public class AsyncServiceImpl implements AsyncService {
    private Logger logger = LogManager.getLogger(AsyncServiceImpl.class);
    @Override
    @Async("asyncServiceExecutor")
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
