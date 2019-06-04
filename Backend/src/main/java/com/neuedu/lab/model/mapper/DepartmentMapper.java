package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Department;

import java.util.List;

public interface DepartmentMapper {
    public List<Department> getAllDepartments();
    public void addDepartment(Department department);
    public void deleteDepartment(String department_id);
    public void updateDepartment(Department department);
    public Department getDepartment(String department_id);
}
