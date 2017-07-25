package com.example.demo;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.TokenRequestParam;
import com.example.demo.filter.TestFilter;
import com.example.demo.util.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class DataControllerTest {

	MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationConnect;
	
	@Autowired
	StringRedisTemplate srt;
	
	@Before
	public void setUp() throws JsonProcessingException {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).addFilter(new TestFilter(), "/data/shop/*").build();
//		mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();
	}

	
	@Test
	public void getPageOverviewTest() throws Exception{
		//get token
		TokenRequestParam rParam = new TokenRequestParam();
		String requestJson = JSONObject.toJSONString(rParam);
		System.out.println(requestJson);
		MvcResult result = mvc.perform(
					MockMvcRequestBuilders.post("/data/tokenC?username=uu")
							.contentType(MediaType.APPLICATION_JSON)
							.content(requestJson))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		//make some params
		HttpSession session = result.getRequest().getSession();
		String username = result.getRequest().getParameter("username");
		String key = username+"_"+session.getId();
		String token = srt.opsForValue().get(key);
		session.setAttribute("username", username);
		session.setAttribute("redis", srt);
		//request with same session
		mvc.perform(
				MockMvcRequestBuilders.get("/data/shop/pageOverview/1/1/1")
				.param("token", token).session((MockHttpSession) session))
		.andDo(MockMvcResultHandlers.print()).andReturn();
		
	}

}
