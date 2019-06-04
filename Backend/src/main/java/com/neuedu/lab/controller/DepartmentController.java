package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.po.Department;
import com.neuedu.lab.model.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/get-all")
//    显示所有科室信息
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @RequestMapping("/get")
//    显示查询科室信息
    public JSONObject getDepartment(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(departmentService.getDepartment(request.getString("department_id")));
    }

    @RequestMapping("/add")
    public JSONObject addDepartment(@RequestBody Department department){
        if(departmentService.addDepartment(department)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/update")
    public JSONObject updateDepartment(@RequestBody Department department){
        if(departmentService.updateDepartment(department)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("/delete")
    public JSONObject deleteDepartment(@RequestBody JSONObject department_id){
        String getString = department_id.getString("department_id");
        if(departmentService.deleteDepartment(getString)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }
}