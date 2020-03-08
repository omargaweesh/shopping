package com.example.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.data.product;

@Repository

public interface product_rep extends JpaRepository<product, Long> {

 
	
}
