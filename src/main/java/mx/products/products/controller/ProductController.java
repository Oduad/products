package mx.products.products.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.products.products.product.Product;

@RestController
public class ProductController {

	@GetMapping(value = "/products")
	public Product findProduct() {
		Product product = new Product();
		product.setId(1L);
		product.setName("Product 1");
		return product;
	}

}