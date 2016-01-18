package com.forest.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.forest.entity.Product;
import com.forest.repository.IProductRepository;

public class JpaProductRepository extends AbstractRepository<Product> implements IProductRepository {
	
	@PersistenceContext
    private EntityManager em;
	
	public JpaProductRepository() {
		super(Product.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

}
