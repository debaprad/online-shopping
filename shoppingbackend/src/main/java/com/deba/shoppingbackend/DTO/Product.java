package com.deba.shoppingbackend.DTO;


import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Product {

	// private fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PRODUCT_ID")
	private int id;
	private String code;
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Category catg;

	@NotBlank(message="product name should not be blank")
	private String name;
	@NotBlank(message="brand should not be blank")
	private String brand;
	@NotBlank(message="description should not be blank")
	private String description;
	@Min(value=1,message="Please Minimum  price")
	private double unitPrice;
	@Min(value=1,message="Please Minimum product")
	private int quantity;
	//@JsonIgnore
	@Column(name = "is_active")
	private boolean active;
	/*@Column(name = "categid")
	private int categId;*/
	private int productCatId;
	private int purchases;
	private int views;
	
	@Transient
	private MultipartFile file;
	
	


	// default constructor
	public Product() {
		
		this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();
		
	}
	
	
	// setters and getters	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	



	public int getPurchases() {
		return purchases;
	}

	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}


	public Category getCatg() {
		return catg;
	}


	public void setCatg(Category catg) {
		this.catg = catg;
	}


	public int getProductCatId() {
		return productCatId;
	}


	public void setProductCatId(int productCatId) {
		this.productCatId = productCatId;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", catg=" + catg + ", name=" + name + ", brand=" + brand
				+ ", description=" + description + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", active="
				+ active + ", productCatId=" + productCatId + ", purchases=" + purchases + ", views=" + views + "]";
	}


	public MultipartFile getFile() {
		return file;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}

	
}
