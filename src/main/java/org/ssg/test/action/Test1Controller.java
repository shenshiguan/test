package org.ssg.test.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test1Controller {

	@RequestMapping("/test1/index")
	public String index1(){
		System.out.println("..............index1.............");
		return "Hello World!";
	}
	
	@RequestMapping("/test2/index")
	public String index2(){
		System.out.println("..............index2.............");
		return "Hello World!";
	}
	
	
}
