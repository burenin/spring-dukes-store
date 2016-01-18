package com.forest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forest.entity.Category;
import com.forest.repository.ICategoryRepository;

@Service
public class CategoryServiceImpl extends AbstractService<Category> implements ICategoryService {
	
	@Autowired
	private ICategoryRepository repository;
	

	@Override
	public ICategoryRepository getRepository() {
		return repository;
	}
}
