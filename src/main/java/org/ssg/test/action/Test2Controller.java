package org.ssg.test.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test2")
public class Test2Controller {

	@RequestMapping("/index2")
	public String index2(){
		System.out.println("........index2......");
//		return "/index.html";
//		return "/index2.html";
		return "/jsp/index.html";
	}

}
