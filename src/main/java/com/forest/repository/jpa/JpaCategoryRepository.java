package com.forest.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.forest.entity.Category;
import com.forest.repository.IRepository;

@org.springframework.stereotype.Repository
public class JpaCategoryRepository extends AbstractRepository<Category> implements IRepository<Category> {
	
    @PersistenceContext
    private EntityManager em;
    
    

	public JpaCategoryRepository() {
		super(Category.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
