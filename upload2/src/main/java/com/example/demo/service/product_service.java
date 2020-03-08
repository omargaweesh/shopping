package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.product;


@Service
public class product_service {

	@Autowired
	private product_rep product;
	
	
	public void saveEmployee(product pro) {
		// TODO Auto-generated method stub
		product.save(pro);
	}
	public Optional<product> getone(Long i) {
		// TODO Auto-generated method stub
		return product.findById(i);
	}
	
	
	public void deleteemp(Long id)
	{
		product.deleteById(id);
	}
	public List<product> getAll() {
		// TODO Auto-generated method stub
		
		return product.findAll();
		
	}
	
}
