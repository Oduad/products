package mx.products.products.validators;

import mx.products.products.entity.Product;

public class ProductValidator {

	public static void save(Product product) {
		if (product.getName()==null || product.getName().trim().isEmpty()) {
			throw new RuntimeException("The name is required.");
		}
		if (product.getName().length()>100) {
			throw new RuntimeException("The name is too long.");
		}
		if (product. getPrice()==null) {
			throw new RuntimeException("The price is required.");
		}
		if (product. getPrice()<0) {
			throw new RuntimeException("The price is incorrect.");
		}
	}
	
	
}
