package com.forest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forest.entity.CustomerOrder;
import com.forest.repository.IOrderRepository;

@Service
public class OrderServiceImpl extends AbstractService<CustomerOrder> implements IOrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private IOrderRepository repository;
	

	@Override
	public IOrderRepository getRepository() {
		return repository;
	}


	@Override
	public List<CustomerOrder> getMyOrders(Integer id) {
		return repository.getOrderByCustomerId(id);
	}
}
