package mx.products.products.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.products.products.converters.ProductConverter;
import mx.products.products.dtos.ProductDTO;
import mx.products.products.entity.Product;
import mx.products.products.service.ProductService;

@RestController
public class ProductController {

	@Autowired 							// Inyecta el bean (instancia creada en ProductRepository)
	private ProductService productService;

	private ProductConverter converter = new ProductConverter();

	@GetMapping(value = "/products/{productId}")
	public ResponseEntity<ProductDTO> findById(@PathVariable("productId") Long productId) {
		Product product = productService.findById(productId);
		ProductDTO productDTO = converter.fromEntity(product);
		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
	}

	@DeleteMapping(value = "/products/{productId}")
	public ResponseEntity<Void> delete(@PathVariable("productId") Long productId) {
		productService.delete(productId);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDTO>> findAll(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {
		
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Product> products = productService.findAll(page);
		List<ProductDTO> dtoProducts = converter.fromEntity(products);
		return new ResponseEntity<List<ProductDTO>>(dtoProducts, HttpStatus.OK);
	}
	
	@PostMapping(value = "/products")
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product) {
		Product newProduct = productService.save(converter.fromDTO(product));
		ProductDTO productDTO = converter.fromEntity(newProduct);
		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.CREATED);
	}

	@PutMapping(value = "/products")
	public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product) {
		Product updateProduct = productService.save(converter.fromDTO(product));
		ProductDTO productDTO = converter.fromEntity(updateProduct);
		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
	}
}