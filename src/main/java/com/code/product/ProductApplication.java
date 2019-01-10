package com.code.product;

import com.code.product.service.CategoryService;
import com.code.product.service.ProductService;
import com.code.product.service.impl.CategoryServiceImpl;
import com.code.product.service.impl.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	public ProductService productService(){
		return new ProductServiceImpl();
	}

	@Bean
	public CategoryService categoryService(){
		return new CategoryServiceImpl();
	}
}

