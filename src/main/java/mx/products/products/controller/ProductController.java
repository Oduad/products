package mx.products.products.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import mx.products.products.dtos.ProductDTO;
import mx.products.products.entity.Product;
import mx.products.products.service.ProductService;

@RestController
public class ProductController {

	//EL CONTROLLER SE VA A SIMPLIFICAR
	
	@Autowired // Inyecta el bean (instancia creada en ProductRepository)
	private ProductService productService;
	
	@GetMapping(value = "/products/{productId}")
	public ResponseEntity<ProductDTO> findById(@PathVariable("productId") Long productId) {
		Product product = productService.findById(productId);
		ProductDTO productDTO = new ProductDTO(product.getId(),product.getName(),product.getPrice()); 
		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/products/{productId}")
	public ResponseEntity<Void> delete(@PathVariable("productId") Long productId) {
		productService.delete(productId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<Product> products = productService.findAll();
		
		/*List<ProductDTO> dtoProducts = products.stream().map(product ->{
			return ProductDTO productDTO = new ProductDTO(product.getId(),product.getName(),product.getPrice());
		}).collect(Collectors.toList());*/
		
		List<ProductDTO> dtoProducts = new ArrayList<>();
		
		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO(product.getId(),product.getName(),product.getPrice());
			dtoProducts.add(productDTO);
		}
		
		return new ResponseEntity<List<ProductDTO>>(dtoProducts, HttpStatus.OK);
	}

	@PostMapping(value = "/products")
	public ResponseEntity<ProductDTO> create(@RequestBody Product product) {
		Product newProduct = productService.save(product);
		
		//List<ProductDTO> dtoProducts = new ArrayList<>();
		
			ProductDTO productDTO = new ProductDTO(newProduct.getId(),newProduct.getName(),newProduct.getPrice());

		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.CREATED);
	}

	@PutMapping(value = "/products")
	public ResponseEntity<ProductDTO> update(@RequestBody Product product) {
		Product updateProduct = productService.save(product);

			ProductDTO productDTO = new ProductDTO(updateProduct.getId(),updateProduct.getName(),updateProduct.getPrice());

		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
	}
}