package com.neuedu.lab.controller;

import com.neuedu.lab.model.po.Constant;
import com.neuedu.lab.model.po.Emp;
import com.neuedu.lab.model.service.ConstantService;
import com.neuedu.lab.model.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService testService;
	@Autowired
	private ConstantService constantService;
	
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

	@RequestMapping("/payment_type")
	public List<Constant> getPaymentType(@RequestBody String constant_type){
		return constantService.getConstant(constant_type);
	}

}
