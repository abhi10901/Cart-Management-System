package com.mycompany.ppms.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mycompany.ppms.entity.Product;
import com.mycompany.ppms.exception.ProductNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
	@ContextConfiguration("classpath:com/mycompany/ppms/dao/test-dao-context.xml"),
	@ContextConfiguration("test-service-context.xml")
})
@WebAppConfiguration
public class ProductServiceTest {
	
	@Autowired
	private ProductService productService;

	@Before
	public void setup() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testFindByNameContainsNiceShoesFoundOne() {
		String name = "Very Nice Shoes";
		String description = "Very Nice Shoes made in Thailand";
		List<Product> products = productService.findByNameContains(name);

		Assert.assertTrue(products.size() == 1);
		Assert.assertEquals(name, products.get(0).getName());
		Assert.assertEquals(description, products.get(0).getDescription());
	}

	@Test
	public void testFindByNameContainsCoolShoesFoundOne() {
		String name = "Cool Shoes";
		String description = "Cool Shoes made in Japan";
		List<Product> products = productService.findByNameContains(name);

		Assert.assertTrue(products.size() == 1);
		Assert.assertEquals(name, products.get(0).getName());
		Assert.assertEquals(description, products.get(0).getDescription());
	}

	@Test
	public void testFindByNameContainsShoesFoundTwo() {
		String keyword = "Shoes";
		String name1 = "Very Nice Shoes";
		String description1 = "Very Nice Shoes made in Thailand";
		String name2 = "Cool Shoes";
		String description2 = "Cool Shoes made in Japan";

		List<Product> products = productService.findByNameContains(keyword);

		Assert.assertTrue(products.size() == 2);
		Assert.assertEquals(name1, products.get(0).getName());
		Assert.assertEquals(description1, products.get(0).getDescription());

		Assert.assertEquals(name2, products.get(1).getName());
		Assert.assertEquals(description2, products.get(1).getDescription());
	}

	@Test(expected = ProductNotFoundException.class)
	public void testAddProductABCSaveToDatabaseAndDeleteIt() {
		String name = "ABC";
		String description = "This is the sample test to check add functionality.";

		Product product = new Product();
		product.setName(name);
		product.setDescription(description);

		Product newProduct = productService.storeProduct(product);

		Assert.assertEquals(product.getName(), newProduct.getName());
		Assert.assertEquals(product.getDescription(), newProduct.getDescription());
		Assert.assertNotNull(newProduct.getId());
		
		List<Product> products = productService.findByNameContains(product.getName());
		
		Assert.assertTrue(products.size() == 1);
		Assert.assertEquals(newProduct.getName(), products.get(0).getName());
		Assert.assertEquals(newProduct.getDescription(), products.get(0).getDescription());
		
		Long abcProductId = products.get(0).getId();
		boolean deleted = productService.deleteProductById(abcProductId);
		Assert.assertTrue(deleted);
		
		products = productService.findByNameContains(name);
		Assert.assertEquals(0, products.size());
		
		deleted = productService.deleteProductById(abcProductId);
		Assert.assertFalse(deleted);

	}
	
	@Test
	public void testFindByNameShoesFoundTwoEditFirstSuccess() {
		String keyword = "Shoes";
		List<Product> products = productService.findByNameContains(keyword);

		Assert.assertTrue(products.size() == 2);
		
		Product product = products.get(1);
		
		String name = "Cool Shoes Budy";
		product.setName(name);
		
		Product newProduct = productService.storeProduct(product);
		
		Assert.assertEquals(newProduct.getName(), name);
		
	}
	
}
