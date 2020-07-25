package mx.products.products.converters;

import mx.products.products.dtos.ProductDTO;
import mx.products.products.entity.Product;

public class ProductConverter extends AbstractConverter<Product, ProductDTO> {

	@Override
	public ProductDTO fromEntity(Product entity) {
		ProductDTO productDTO = new ProductDTO(entity.getId(),entity.getName(),entity.getPrice());
		return productDTO;
	}

	@Override
	public Product fromDTO(ProductDTO dto) {
		Product product = new Product(dto.getId(),dto.getName(),dto.getPrice());
		return product;
	}
}
