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
        System.out.println((departmentService.getDepartment("AA").get(0)).toString());
        System.out.println((departmentService.getDepartment("AA").get(1)).toString());

    }

    @Test
    public void testGetAllDepartments(){
        System.out.println((departmentService.getAllDepartments().get(0)).toString());
        System.out.println((departmentService.getAllDepartments().get(1)).toString());

    }

    @Test
    public void testUpdateDepartment(){
        Department department = departmentService.getDepartment("AAAAAAAB").get(0);
        department.setDepartment_cat("修改了");
        department.setDepartment_name("yeap");
        department.setDepartment_type("还行");
        assertTrue(departmentService.updateDepartment(department));

    }

    @Test
    public void testDeleteDepartment(){
        Department department = departmentService.getDepartment("AAAAAAAB").get(0);
        assertTrue(departmentService.deleteDepartment(department));
    }
}