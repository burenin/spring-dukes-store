package com.forest.repository.jpa;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.dao.DataAccessException;

import com.forest.repository.IRepository;

public abstract class AbstractRepository<T> implements IRepository<T> {
	
	private Class<T> entityClass;
	
	public AbstractRepository() {
        
    }

    public AbstractRepository(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }
	
	@Override
	public void save(T entity) throws DataAccessException {
		getEntityManager().merge(entity);

	}

	@Override
	public void delete(T entity) throws DataAccessException {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	@Override
	public T findById(Object id) throws DataAccessException {
		return getEntityManager().find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		CriteriaQuery<T> cq = (CriteriaQuery<T>)getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	@Override
	public List<T> findRange(int[] range) {
		CriteriaQuery<T> cq = (CriteriaQuery<T>)getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
        return q.getResultList();
	}

	@Override
	public List<T> findRange(int[] range, CriteriaQuery<T> query) {
		Query q = getEntityManager().createQuery(query);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
	}

	@Override
	public int count() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

}
