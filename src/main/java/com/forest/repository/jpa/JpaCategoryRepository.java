package com.forest.repository.jpa;

import org.springframework.stereotype.Repository;

import com.forest.entity.Category;
import com.forest.repository.ICategoryRepository;

@Repository
public class JpaCategoryRepository extends AbstractRepository<Category> implements ICategoryRepository {
	
	public JpaCategoryRepository() {
		super(Category.class);
	}
}
