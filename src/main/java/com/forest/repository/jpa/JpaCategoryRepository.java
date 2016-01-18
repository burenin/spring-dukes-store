package com.forest.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.forest.entity.Category;
import com.forest.repository.ICategoryRepository;

@Repository
public class JpaCategoryRepository extends AbstractRepository<Category> implements ICategoryRepository {
	
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
