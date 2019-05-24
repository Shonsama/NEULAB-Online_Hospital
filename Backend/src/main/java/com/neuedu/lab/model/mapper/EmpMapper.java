package com.neuedu.lab.model.mapper;

import java.util.List;

import com.neuedu.lab.model.po.Emp;

public interface EmpMapper {
	
	public List<Emp> getEmps(Emp condition);

}
