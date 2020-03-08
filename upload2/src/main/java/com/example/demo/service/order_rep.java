package com.example.demo.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.product_in_order;

public interface order_rep extends JpaRepository<product_in_order,Long> {

	Optional<product_in_order> findProduct_in_orderByName(String name);
}
