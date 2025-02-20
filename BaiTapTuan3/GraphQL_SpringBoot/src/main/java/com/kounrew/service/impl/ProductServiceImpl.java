package com.kounrew.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kounrew.entity.ProductEntity;
import com.kounrew.model.ProductInput;
import com.kounrew.repository.ProductRepository;
import com.kounrew.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository repository;
	
	@Override
	public List<ProductEntity> findAllProduct() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Optional<ProductEntity> findByProductId(int productId) {
		// TODO Auto-generated method stub
		return repository.findById(productId);
	}

	@Override
	public ProductEntity save(ProductInput productInput) {
		// TODO Auto-generated method stub
		ProductEntity product = new ProductEntity();
        product.setTitle(productInput.getTitle());
        product.setDescription(productInput.getDescription());
        product.setImage(productInput.getImage());
        product.setAmount(productInput.getAmount());
		return repository.save(product);
	}
	
}
