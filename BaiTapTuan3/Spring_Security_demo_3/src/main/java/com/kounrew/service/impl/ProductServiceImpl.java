package com.kounrew.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kounrew.entity.Product;
import com.kounrew.repository.ProductRepository;
import com.kounrew.service.ProductServices;

@Service
public class ProductServiceImpl implements ProductServices{
	@Autowired
	private ProductRepository repo;
	
	public ProductServiceImpl(ProductRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public List<Product> listAll(){
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public Product get(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return repo.save(product);
	}
	
	
	
}
