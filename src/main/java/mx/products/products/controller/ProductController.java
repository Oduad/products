package mx.products.products.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.products.products.entity.Product;

@RestController
public class ProductController {

	private List<Product> products = new ArrayList<>();
	
	public ProductController() {
		for (int c = 0; c < 10; c++) {
			products.add(new Product(c + 1L, "Product " + (c + 1L)));
		}
	}
	
	@GetMapping(value ="/products")
	public List<Product> findAll(){
		return this.products;
	}
	
	@PostMapping(value ="/products")
	 public Product create(@RequestBody Product product) {//-->Lo que viene en la carga útil se inyecta en esta variable.
		//Va a hacer algo llamado margering, todos los valores del JSon los trata de casar en la clase Producto
		this.products.add(product);
		return product;
	}
	
	@PutMapping(value ="/products")
		public Product update(@RequestBody Product product) {
			 for (Product prod: this.products) {
				if (prod.getId().longValue()== product.getId().longValue()) {
					prod.setName(product.getName());
					return prod;
				}
			}
			 throw new RuntimeException("This product does not exist");
	}
	
	@GetMapping(value ="/products/{productId}")
	 public Product findById(@PathVariable("productId") Long productId) {
		 for (Product prod: this.products) {
			if (prod.getId().longValue()== productId.longValue()) {
				return prod;
			}
		}
		 return null;
	 }
	
	@DeleteMapping(value = "/products/{productId}")
		public void delete(@PathVariable("productId") Long productId) {
		Product deleteProduct = null;
		for (Product prod: this.products) {
			if (prod.getId().longValue()== productId.longValue()) {
				deleteProduct = prod;
				break;
			}
		}
		if (deleteProduct == null) {
			throw new RuntimeException("This product does not exist");
		}
		this.products.remove(deleteProduct);
	}
}