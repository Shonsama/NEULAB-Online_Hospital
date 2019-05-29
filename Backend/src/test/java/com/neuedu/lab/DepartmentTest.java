package com.neuedu.lab;

import com.neuedu.lab.model.po.Constant;
import com.neuedu.lab.model.po.Department;
import com.neuedu.lab.model.service.ConstantService;
import com.neuedu.lab.model.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = LabApplication.class)
public class DepartmentTest {
    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testAddDepartment(){
        Department department = new Department();
        department.setDepartment_id("AAAAAAAB");
        department.setDepartment_cat("临床科室");
        department.setDepartment_name("小儿麻痹");
        department.setDepartment_type("传染科");

        assertTrue(departmentService.addDepartment(department));

    }

    @Test
    public void testGetDepartment(){
        System.out.println((departmentService.getDepartment("AZBK").get(0)).toString());

    }

    @Test
    public void testUpdateDepartment(){
        Department department = departmentService.getDepartment("AAGEGHAP").get(0);

        assertTrue(departmentService.deleteDepartment(department));

        Department newDepartment = new Department();
        newDepartment.setDepartment_id("AAGEGHAP");
        newDepartment.setDepartment_cat("临床科室");
        newDepartment.setDepartment_name("小儿麻痹");
        newDepartment.setDepartment_type("传染科");

        assertTrue(departmentService.addDepartment(newDepartment));

    }

    @Test
    public void testDeleteDepartment(){
        Department department = departmentService.getDepartment("AAGEGHAP").get(0);

        assertTrue(departmentService.deleteDepartment(department));
    }
}