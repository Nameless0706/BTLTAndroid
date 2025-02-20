package com.kounrew.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.kounrew.entity.ProductEntity;
import com.kounrew.model.ProductInput;
import com.kounrew.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@QueryMapping
	public List<ProductEntity> products(){
		return productService.findAllProduct();
	}
	
	@QueryMapping
	public Optional<ProductEntity> productById(@Argument int id){
		return productService.findByProductId(id);
	}
	
	@MutationMapping
    public ProductEntity createProduct(@Argument("product") ProductInput productInput) {
        return productService.save(productInput);
    }
	
	
	
	
}
