package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	@Query(value = "SELECT * FROM sale ORDER BY sales DESC LIMIT 1", nativeQuery = true)
	Sale getTopSellingProduct();
}