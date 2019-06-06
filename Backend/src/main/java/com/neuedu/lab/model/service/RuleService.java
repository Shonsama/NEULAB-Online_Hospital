package com.neuedu.lab.model.service;

import com.neuedu.lab.model.mapper.DepartmentMapper;
import com.neuedu.lab.model.po.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RuleService {
    @Resource
    private DepartmentMapper departmentMapper;


    public List<Department> getAllDepartments(){
        return departmentMapper.getAllDepartments();
    }

    public Department getDepartment(String department_id){
        return departmentMapper.getDepartment(department_id);
    }

    public boolean addDepartment(Department department){
        try {
            departmentMapper.addDepartment(department);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteDepartment(String depatment_id){
        try {
            departmentMapper.deleteDepartment(depatment_id);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateDepartment(Department department){
        try {
            departmentMapper.updateDepartment(department);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }



}
