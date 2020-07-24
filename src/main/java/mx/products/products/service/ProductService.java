package mx.products.products.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.products.products.entity.Product;
import mx.products.products.repository.ProductRepository;
import mx.products.products.validators.ProductValidator;

@Service // -Le dice a que cree una instancia de este objeto para poder inyectarlo en otros objetos con @Autowired
public class ProductService {

	// El producto debe proporcionar todos los métodos de negocios que necesite el
	// Controller

	@Autowired // Inyecta el bean (instancia creada en ProductRepository)
	private ProductRepository productRepo;

	public Product findById(Long productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("This product does not exist."));
		return product;
	}

	@Transactional
	public void delete(Long productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("This product does not exist."));
		productRepo.delete(product);
	}

	public List<Product> findAll() {
		List<Product> products = productRepo.findAll();
		return products;
	}

	/*public Product create(Product product) {
	Product newProduct = productRepo.save(product);
	return newProduct;
	}

	public Product update(Product product) {
	Product existProduct = productRepo.findById(product.getId())
			.orElseThrow(() -> new RuntimeException("This product does not exist."));
	existProduct.setName(product.getName());
	existProduct.setPrice(product.getPrice());
	productRepo.save(existProduct);
	return existProduct;
	}*/
	
	@Transactional		//Debe ser transaccional
	public Product save(Product product) {
		
		ProductValidator.save(product);
		
		if (product.getId() == null) {
			Product newProduct = productRepo.save(product);
			return newProduct;
		}
		Product existProduct = productRepo.findById(product.getId())
				.orElseThrow(() -> new RuntimeException("This product does not exist."));
		existProduct.setName(product.getName());
		existProduct.setPrice(product.getPrice());
		productRepo.save(existProduct);
		return existProduct;
	}
}
