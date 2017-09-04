package com.deba.shoppingbackend.DTO;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.JoinColumn;

@Entity
public class Category {

	
	public Category() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CATEGORY_ID")
	private int id;
	@NotBlank(message="Category Name Can't be Blank")
	private String name;
	@NotBlank(message="Description Can't be Blank")
	private String description;
	private String imageUrl;
	private boolean active=true;
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)/*
	@JoinTable(name = "Category_Product", 
				joinColumns = { @JoinColumn(name = "CATEGORY_ID") },
				inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID") })*/
	private Set<Product> products;
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
				+ ", active=" + active + ", products=" + products + "]";
	}
	public Category(int id, String name, String description, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}
	
	
	public Set<Product> getProducts() {
		return products;
	}
	
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
}
