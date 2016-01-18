package com.forest.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.forest.entity.CustomerOrder;
import com.forest.repository.IOrderRepository;

public class JpaOrderRepository extends AbstractRepository<CustomerOrder> implements IOrderRepository {

	@PersistenceContext
    private EntityManager em;
    
    

	public JpaOrderRepository() {
		super(CustomerOrder.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> getOrderByCustomerId(Integer id) {
        Query createNamedQuery = getEntityManager().createNamedQuery("CustomerOrder.findByCustomerId");

        createNamedQuery.setParameter("id", id);

        return createNamedQuery.getResultList();
    }

}
