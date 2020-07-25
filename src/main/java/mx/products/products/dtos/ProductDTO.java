package mx.products.products.dtos;
/**/
public class ProductDTO {
	
	private Long id;
	private String name;	
	private Double price;
	
	public ProductDTO(Long id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public ProductDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
