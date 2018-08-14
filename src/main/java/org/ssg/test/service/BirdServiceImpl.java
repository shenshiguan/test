package org.ssg.test.service;

import com.alibaba.dubbo.config.annotation.Service;

@Service(
		version = "1.0.0"
)
public class BirdServiceImpl implements BirdService {

	@Override
	public String sayHello(String name) {
		System.out.println("sayHello..............................");
		return "Hello,"+name;
	}

}
