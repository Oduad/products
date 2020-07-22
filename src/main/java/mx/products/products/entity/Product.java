package mx.products.products.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Vamos a hacerla persistida en la BD

@Entity														// Es la más importante
@Table(name = "product")									// Dice vs qué tabla de la BD mapea
public class Product {
	
	@Id
	@Column(name ="id")										// Vs qué columna mapea
	@GeneratedValue(strategy=GenerationType.IDENTITY)		// Se incrementa del lado de la BD
	private Long id;
	
	@Column(name ="name", nullable = false, length = 100)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String name;	
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	public Product(Long id,String name, Double price){
		this.id = id;
		this.name = name;
		this.price = price;
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
