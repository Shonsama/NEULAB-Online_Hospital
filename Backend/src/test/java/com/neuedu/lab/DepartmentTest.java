package com.neuedu.lab;

import com.neuedu.lab.model.service.DepartmentService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = LabApplication.class)
public class DepartmentTest {
    @Autowired
    private DepartmentService departmentService;

}