package com.forest.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.forest.entity.Product;
import com.forest.repository.IProductRepository;

public class ProductServiceImpl extends AbstractService<Product> implements IProductService{
	
	@Autowired
	private IProductRepository repository;

	@Override
	public IProductRepository getRepository() {
		return repository;
	}

}
