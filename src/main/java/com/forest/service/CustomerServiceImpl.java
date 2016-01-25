package com.forest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forest.entity.Customer;
import com.forest.repository.ICustomerRepository;

@Service
public class CustomerServiceImpl extends AbstractService<Customer> implements ICustomerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private ICustomerRepository repository;
	

	@Override
	public ICustomerRepository getRepository() {
		return repository;
	}
}
