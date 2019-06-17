package com.neuedu.lab.controller;

import com.neuedu.lab.model.service.AsyncService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("/test")
@RestController
public class TestAsyncController {
    public static Logger logger = org.apache.logging.log4j.LogManager.getLogger(TestAsyncController.class);

    @Autowired
    private AsyncService asyncService;
    @RequestMapping("/")
    public String success(){
        logger.info("start submit");
        //调用service层的任务
        asyncService.executeAsync();
        logger.info("end submit");
        return "success";
    }
}
