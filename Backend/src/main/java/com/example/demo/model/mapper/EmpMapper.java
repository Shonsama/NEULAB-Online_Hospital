package com.example.demo.model.mapper;

import java.util.List;

import com.example.demo.model.po.Emp;

public interface EmpMapper {
	
	public List<Emp> getEmps(Emp condition);

}
