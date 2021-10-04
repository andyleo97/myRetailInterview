package com.andyleo.myRetail;

import com.andyleo.myRetail.controller.ProductController;
import com.andyleo.myRetail.model.Product;
import com.andyleo.myRetail.service.ProductService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest
class MyRetailApplicationTests {

	@Test
	void contextLoads() {}

}
