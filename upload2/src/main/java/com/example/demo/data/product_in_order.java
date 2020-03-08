package com.example.demo.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity 
public class product_in_order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private double price;
	private String picpath;
	private int p_quantity;
	private double total;
	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private product product;

	
	
	
	

	public product_in_order() {
		super();
	}


	public product_in_order(Long id, String name, double price, String picpath, int p_quantity, double total,
			com.example.demo.data.product product) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.picpath = picpath;
		this.p_quantity = p_quantity;
		this.total = total;
		this.product = product;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getPicpath() {
		return picpath;
	}


	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}


	public int getP_quantity() {
		return p_quantity;
	}


	public void setP_quantity(int p_quantity) {
		this.p_quantity = p_quantity;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public product getProduct() {
		return product;
	}


	public void setProduct(product product) {
		this.product = product;
	}
	
	
	

	
}
