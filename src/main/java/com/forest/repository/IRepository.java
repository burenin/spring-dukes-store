package com.forest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.dao.DataAccessException;

public interface IRepository<T> {
	
	EntityManager getEntityManager();
	
	T save(T entity) throws DataAccessException;
	
	void delete (T entity) throws DataAccessException;
	
	T findById(Object id) throws DataAccessException;
	
	List<T> findAll();
	
	List<T> findRange(int[] range);

	List<T> findRange(int[] range, CriteriaQuery<T> query);
	
	int count();
}
