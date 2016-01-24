package com.forest.repository.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.forest.entity.CustomerOrder;
import com.forest.repository.IOrderRepository;

@Repository
public class JpaOrderRepository extends AbstractRepository<CustomerOrder> implements IOrderRepository {
	
	public JpaOrderRepository() {
		super(CustomerOrder.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> getOrderByCustomerId(Integer id) {
        Query createNamedQuery = getEntityManager().createNamedQuery("CustomerOrder.findByCustomerId");

        createNamedQuery.setParameter("id", id);

        return createNamedQuery.getResultList();
    }

}
