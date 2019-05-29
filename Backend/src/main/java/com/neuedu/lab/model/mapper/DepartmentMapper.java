package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Department;

import java.util.List;

public interface DepartmentMapper {
    public List<Department> getAllDepartments();
    public void addDepartment(Department department);
    public void deleteDepartment(Department department);
    public void updateDepartment(Department department);
    public List<Department> getDepartment(String department_id);
}
