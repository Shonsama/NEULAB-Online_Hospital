package com.neuedu.lab.controller;

import com.neuedu.lab.model.po.Department;
import com.neuedu.lab.model.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/test")
    public List<Department> getDepartment(){
        return departmentService.getDepartment("AAA");
    }


    @RequestMapping("/get")
    public void getDepartment(@RequestParam String department_id){
        departmentService.getDepartment(department_id);
    }

    @RequestMapping("/add")
    public String addDepartment(@RequestParam Department department){
        if(departmentService.addDepartment(department)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/update")
    public String updateConstant(@RequestParam Department department){
        if(departmentService.updateDepartment(department)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

    @RequestMapping("/delete")
    public String deleteConstant(@RequestParam Department department){
        if(departmentService.deleteDepartment(department)){
            return "{\"result\":\"success\"}";
        }
        else {
            return "{\"result\":\"fail\"}";
        }
    }

}