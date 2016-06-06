package com.mycompany.ppms;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
	@ContextConfiguration("classpath:com/mycompany/ppms/dao/test-dao-context.xml"),
	@ContextConfiguration("classpath:com/mycompany/ppms/service/test-service-context.xml"),
	@ContextConfiguration("test-servlet-context.xml")
})
public class ProductAddTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testAddProduct() throws Exception {
		String name = "ABC";
		String description = "This is the sample product";
		this.mockMvc.perform(post("/product/")
				.param("name", name)
				.param("description", description)
				.sessionAttr("product", new Product())
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.status").value("created"))
			//.andExpect(jsonPath("$.product.id").value(1))
			.andExpect(jsonPath("$.product.name").value(name))
			.andExpect(jsonPath("$.product.description").value(description));
			//.andExpect(model().attribute("status", "product_created"))
			//.andExpect(model().attribute("product", hasPro));
	}
}
