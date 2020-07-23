package mx.products.products.controller;

import java.util.ArrayList;
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
import mx.products.products.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired // Inyecta el bean (instancia creada en ProductRepository)
	private ProductRepository productRepo;

	/*private List<Product> products = new ArrayList<>();

	public ProductController() {
		for (int c = 0; c < 10; c++) {
			products.add(new Product(c + 1L, "Product " + (c + 1L), 100D * (1 + c)));
		}
	}*/

	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = productRepo.findAll();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@PostMapping(value = "/products")
	public ResponseEntity<Product> create(@RequestBody Product product) {
		Product newProduct = productRepo.save(product);
		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
	}

	@PutMapping(value = "/products")
	public ResponseEntity<Product> update(@RequestBody Product product) {
		Product existProduct = productRepo.findById(product.getId())
				.orElseThrow(() -> new RuntimeException("This product does not exist."));
		existProduct.setName(product.getName());
		existProduct.setPrice(product.getPrice());
		productRepo.save(existProduct);
		return new ResponseEntity<Product>(existProduct, HttpStatus.OK);
	}

	@GetMapping(value = "/products/{productId}")
	public ResponseEntity findById(@PathVariable("productId") Long productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("This product does not exist."));
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@DeleteMapping(value = "/products/{productId}")
	public ResponseEntity delete(@PathVariable("productId") Long productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("This product does not exist."));
		productRepo.delete(product);
		return new ResponseEntity(HttpStatus.OK);
	}
}