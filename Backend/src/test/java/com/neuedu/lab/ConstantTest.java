package com.neuedu.lab;

import com.neuedu.lab.model.po.Constant;
import com.neuedu.lab.model.service.ConstantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = LabApplication.class)
public class ConstantTest {
    @Autowired
    private ConstantService constantService;

    @Test
    public void testAddConstant(){
        Constant constant = new Constant();
        constant.setConstant_type("payment_type_test");
        constant.setConstant_name("李雨泽代付");

        assertTrue(constantService.addConstant(constant));
    }

    @Test
    public void testGetConstant(){
        System.out.println(constantService.getConstant("payment_type_test").get(0).toString());
    }

    @Test
    public void testUpdateConstant(){
        Constant constant = constantService.getConstant("payment_type_test").get(0);

        constant.setConstant_name("王三奇代付");

        assertTrue(constantService.updateConstant(constant));

    }

    @Test
    public void testDeleteConstant(){
        Constant constant = constantService.getConstant("payment_type_test").get(0);

        assertTrue(constantService.deleteConstant(constant));
    }



}
