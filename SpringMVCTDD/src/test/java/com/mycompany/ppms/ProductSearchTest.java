package com.mycompany.ppms;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
	@ContextConfiguration("classpath:com/mycompany/ppms/dao/test-dao-context.xml"),
	@ContextConfiguration("classpath:com/mycompany/ppms/service/test-service-context.xml"),
	@ContextConfiguration("test-servlet-context.xml")
})
public class ProductSearchTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testSearchProduct() throws Exception {
		String keyword = "Very Nice Shoes";
		this.mockMvc.perform(get("/product/search")
				.param("q", keyword)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value("found"))
		.andExpect(jsonPath("$.products[0].name").value(keyword));
	}
	
	@Test
	public void testSearchProductByNameNotFound() throws Exception{
		String product = "Soft Shoes";
		String infoText = String.format("Could not find any product matches '%s'", product);
		
		this.mockMvc.perform(get("/product/search")
				.param("q", product)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value("not found"))
		.andExpect(jsonPath("$.text").value(infoText));
	}
	
	@Test
	public void testSearchProductByNameCoolShoesFoundOne() throws Exception {
		
		String keyword = "Cool Shoes";
		this.mockMvc.perform(get("/product/search")
			.param("q", keyword)
			.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value("found"))
		.andExpect(jsonPath("$.products[0].name").value("Cool Shoes"));
			
	}
	
	@Test
	public void testSearchProductByNameShoeFoundTwo() 
			throws Exception {
		String search = "Shoes";
				
		this.mockMvc.perform(get("/product/search")
			.param("q", search)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.status").value("found"))
			.andExpect(jsonPath("$.products[0].name").value("Very Nice Shoes"))
			.andExpect(jsonPath("$.products[1].name").value("Cool Shoes"));
	}
}
