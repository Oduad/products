package mx.products.products.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import mx.products.products.entity.Product;
import mx.products.products.exceptions.GenerlServiceException;
import mx.products.products.exceptions.NoDataFoundException;
import mx.products.products.exceptions.ValidateServiceException;
import mx.products.products.repository.ProductRepository;
import mx.products.products.validators.ProductValidator;

@Service // -Le dice a que cree una instancia de este objeto para poder inyectarlo en otros objetos con @Autowired
public class ProductService {

	// El producto debe proporcionar todos los métodos de negocios que necesite el
	// Controller

	@Autowired // Inyecta el bean (instancia creada en ProductRepository)
	private ProductRepository productRepo;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	public Product findById(Long productId) {
		try {
			Product product = productRepo.findById(productId)
					.orElseThrow(() -> new 	NoDataFoundException("This product does not exist."));
			return product;
		}catch(ValidateServiceException | NoDataFoundException e) {
			LOGGER.info(e.getMessage(), e);
			throw e;
		}catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new GenerlServiceException(e.getMessage(),e);
		}
	}

	@Transactional
	public void delete(Long productId) {
		try {
			Product product = productRepo.findById(productId)
					.orElseThrow(() -> new NoDataFoundException("This product does not exist."));
			productRepo.delete(product);
		} catch (ValidateServiceException | NoDataFoundException e) {
			LOGGER.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new GenerlServiceException(e.getMessage(), e);
		}
	}

	public List<Product> findAll(Pageable page) {				// -Agregamos Pageable page como parámetro
		try {
			List<Product> products = productRepo.findAll(page).toList();
			return products;	
		} catch (ValidateServiceException | NoDataFoundException e) {
			LOGGER.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new GenerlServiceException(e.getMessage(), e);
		}
	}

	public Product save(Product product) {
		try {
			ProductValidator.save(product);
			
			if (product.getId() == null) {
				Product newProduct = productRepo.save(product);
				return newProduct;
			}
			Product existProduct = productRepo.findById(product.getId())
					.orElseThrow(() -> new NoDataFoundException("This product does not exist."));
			existProduct.setName(product.getName());
			existProduct.setPrice(product.getPrice());
			productRepo.save(existProduct);
			return existProduct;
		} catch (ValidateServiceException | NoDataFoundException e) {
			LOGGER.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new GenerlServiceException(e.getMessage(), e);
		}
	}
}
