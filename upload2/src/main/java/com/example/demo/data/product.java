package com.example.demo.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class product implements Serializable {

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
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "product")
	@JsonIgnore
	private Set<product_in_order> orders=new HashSet<product_in_order>();
	
	
	
	
	public product() {
		super();
	}
	public product(Long id, String name, double price, String picpath, Set<product_in_order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.picpath = picpath;
		this.orders = orders;
	}
	
	@Override
	public String toString() {
		return "product [id=" + id + ", name=" + name + ", price=" + price + ", picpath=" + picpath + ", orders="
				+ orders + "]";
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
	public Set<product_in_order> getOrders() {
		return orders;
	}
	public void setOrders(Set<product_in_order> orders) {
		this.orders = orders;
	}
	
	
	

	
}
