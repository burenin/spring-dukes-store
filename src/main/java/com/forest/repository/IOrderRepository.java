package com.forest.repository;

import java.util.List;

import com.forest.entity.CustomerOrder;

public interface IOrderRepository extends IRepository<CustomerOrder> {

	List<CustomerOrder> getOrderByCustomerId(Integer id);
	
}
