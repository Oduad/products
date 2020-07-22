package mx.products.products.entity;

public class Product {
	
	String name;
	Long id;
	//
	
	public Product(Long id,String name){
		this.id = id;
		this.name = name;
	}
	
	public Product(){
	
	}
	
	public String getName() {
		return name;
	}
	public Long getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
