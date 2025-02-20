package com.kounrew.service;

import java.util.List;
import java.util.Optional;

import com.kounrew.entity.ProductEntity;
import com.kounrew.model.ProductInput;

public interface ProductService {
	List<ProductEntity> findAllProduct();
	
	Optional<ProductEntity> findByProductId(int productId);
	
	public ProductEntity save(ProductInput productInput);
}
