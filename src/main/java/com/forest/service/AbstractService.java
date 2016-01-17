package com.forest.service;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.springframework.dao.DataAccessException;

import com.forest.entity.Category;
import com.forest.util.AbstractPaginationHelper;

public abstract class AbstractService<T> implements IService<T> {
	
	private AbstractPaginationHelper<T> 		pagination;
	private List<T> 							items;

	@Override
	public void save(T entity) throws DataAccessException {
		getRepository().save(entity);
	}

	@Override
	public void delete(T entity) throws DataAccessException {
		getRepository().delete(entity);
	}

	@Override
	public T findById(Object id) throws DataAccessException {
		return getRepository().findById(id);
	}

	@Override
	public List<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	public List<T> findRange(int[] range) {
		return getRepository().findRange(range);
	}

	@Override
	public List<T> findRange(int[] range, CriteriaQuery<T> query) {
		return getRepository().findRange(range, query);
	}

	@Override
	public int count() {
		return getRepository().count();
	}
	
	public AbstractPaginationHelper<T> getPagination() {
		if (pagination == null) {
            pagination = new AbstractPaginationHelper<T>(AbstractPaginationHelper.DEFAULT_SIZE) {

                @Override
                public int getItemsCount() {
                    return count();
                }

                @Override
                public List<T> createPageDataModel() {
                    return findRange(new int[]{getPageFirstItem(), 
                        getPageFirstItem() + getPageSize()});
                }
            };
        }
        return pagination;
	}
	
	
	
	@Override
	public List<T> getItems() {
		if (items == null){
			items = getPagination().createPageDataModel();
		}
		return items;
	}
}
