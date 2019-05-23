package com.example.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.mapper.EmpMapper;

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
