package com.deba.shoppingbackend.DTO;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity
public class Category {

	
	public Category() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="CATEGORY_ID")
	private int id;
	private String name;
	private String description;
	private String imageUrl;
	private boolean active=true;
	/*private Set<Product> products;*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", active=" + active + "]";
	}
	public Category(int id, String name, String description, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}
	
	/*@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Category_Product", 
				joinColumns = { @JoinColumn(name = "CATEGORY_ID") },
				inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID") })
	public Set<Product> getProducts() {
		return products;
	}
	
	public void setProducts(Set<Product> products) {
		this.products = products;
	}*/
	
	
}
