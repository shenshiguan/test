package org.ssg.test.action;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class Test1ControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(Test1ControllerTest.class);


	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Before
	public void setup(){
		mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testindex1(){
		String response = "";
		try {
			response = mockMvc.perform(MockMvcRequestBuilders.get("/test1/index")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("response:"+response);
	}
	
	@Test
	public void testindex2(){
		String response = "";
		try {
			response = mockMvc.perform(MockMvcRequestBuilders.get("/test2/index")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("response:"+response);
	}
}
