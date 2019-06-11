package com.neuedu.lab;


import com.neuedu.lab.model.service.RegisterService;
import com.neuedu.lab.model.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LabApplication.class)
public class ServiceTest {
    @Autowired
    private UserService userService;




}
