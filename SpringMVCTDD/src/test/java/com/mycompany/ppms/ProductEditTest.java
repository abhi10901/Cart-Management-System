package com.mycompany.ppms;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mycompany.ppms.entity.Product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ 
	@ContextConfiguration("classpath:com/mycompany/ppms/dao/test-dao-context.xml"),
	@ContextConfiguration("classpath:com/mycompany/ppms/service/test-service-context.xml"),
	@ContextConfiguration("test-servlet-context.xml") })
public class ProductEditTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testEditProductNameToCoolShoesBudy() throws Exception {
		this.mockMvc.perform(put("/product/")
				.param("name", "Cool Shoes Budy")
				.param("id", "2")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.sessionAttr("product", new Product()))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value("updated"))
		.andExpect(jsonPath("$.product.name").value("Cool Shoes Budy"));
	}
}
