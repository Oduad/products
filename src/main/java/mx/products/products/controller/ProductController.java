package mx.products.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.products.products.entity.Product;
import mx.products.products.service.ProductService;

@RestController
public class ProductController {

	//EL CONTROLLER SE VA A SIMPLIFICAR
	
	@Autowired // Inyecta el bean (instancia creada en ProductRepository)
	private ProductService productService;
	
	@GetMapping(value = "/products/{productId}")
	public ResponseEntity<Product> findById(@PathVariable("productId") Long productId) {
		Product product = productService.findById(productId);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/products/{productId}")
	public ResponseEntity<Void> delete(@PathVariable("productId") Long productId) {
		productService.delete(productId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = productService.findAll();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@PostMapping(value = "/products")
	public ResponseEntity<Product> create(@RequestBody Product product) {
		Product newProduct = productService.save(product);
		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
	}

	@PutMapping(value = "/products")
	public ResponseEntity<Product> update(@RequestBody Product product) {
		Product updateProduct = productService.save(product);
		return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);
	}
}