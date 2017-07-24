package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.HelloWorldController;

@RunWith(SpringJUnit4ClassRunner.class)
// 指定SpringBoot工程的Application启动类
// @SpringBootTest 取代@SpringApplicationConfiguration @WebAppConfiguration
// @WebAppConfiguration理由：web项目，Junit需要模拟ServletContext。
@SpringBootTest(classes = MockServletContext.class)
public class HelloWorldControllerTest {
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController())
				.build();
	}
	
	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/"))
		.andDo(MockMvcResultHandlers.print())
		.andReturn();
	}
	
	@Test
	public void getUser() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/getUser"))
		.andDo(MockMvcResultHandlers.print())
		.andReturn();
	}
	
	
}
