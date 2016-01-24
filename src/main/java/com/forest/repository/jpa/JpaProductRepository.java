package com.forest.repository.jpa;

import org.springframework.stereotype.Repository;

import com.forest.entity.Product;
import com.forest.repository.IProductRepository;

@Repository
public class JpaProductRepository extends AbstractRepository<Product> implements IProductRepository {
	
	
	public JpaProductRepository() {
		super(Product.class);
	}
}
