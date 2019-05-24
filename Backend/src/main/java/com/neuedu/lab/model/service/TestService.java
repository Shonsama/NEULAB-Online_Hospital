package com.neuedu.lab.model.service;

import com.neuedu.lab.model.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	@Autowired
	private EmpMapper empMapper;
	
	public void test()
	{
		System.out.println(empMapper);
		System.out.println("test");
	}

}
