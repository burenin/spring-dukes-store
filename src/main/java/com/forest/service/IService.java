package com.forest.service;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.springframework.dao.DataAccessException;

import com.forest.repository.IRepository;

public interface IService<T> {
	IRepository<T> getRepository();
	
	void save(T entity) throws DataAccessException;
	
	void delete (T entity) throws DataAccessException;
	
	T findById(Object id) throws DataAccessException;
	
	List<T> findAll();
	
	List<T> findRange(int[] range);

	List<T> findRange(int[] range, CriteriaQuery<T> query);
	
	int count();
	
	List<T> getItems();
}
