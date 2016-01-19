package com.forest.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.forest.entity.Product;
import com.forest.repository.IProductRepository;

@Repository
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
