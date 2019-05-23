package com.example.demo.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.po.Emp;
import com.example.demo.model.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping("/test")
	public String test()
	{
		return "{\"result\":true}";
	}
	
	@RequestMapping("/emp")
	public Emp getOneEmp()
	{
		testService.test();
		
		Emp e = new Emp();
		e.setEmpno(1);
		e.setEname("feiyy");
		e.setHiredate(new Date(System.currentTimeMillis()));
		
		return e;
	}

}
