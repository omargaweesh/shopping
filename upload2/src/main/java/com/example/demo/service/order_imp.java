package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.product_in_order;

@Service
public class order_imp {

	@Autowired
	 order_rep order;
	
	
	public List<product_in_order> getAll() {
		return order.findAll();
	}
	
	public Optional<product_in_order> getone(Long id) {
		return order.findById(id);
	}
	public Optional<product_in_order> getbyname(String name) {
		return order.findProduct_in_orderByName(name);
	}
	public void DelteAll() {
		 order.deleteAll();
	}
	public void save(product_in_order product) {
		 order.save(product);
	}
	
}
