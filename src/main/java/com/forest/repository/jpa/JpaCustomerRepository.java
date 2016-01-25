package com.forest.repository.jpa;

import org.springframework.stereotype.Repository;

import com.forest.entity.Customer;
import com.forest.repository.ICustomerRepository;

@Repository
public class JpaCustomerRepository extends AbstractRepository<Customer> implements ICustomerRepository {

	public JpaCustomerRepository(){
		super(Customer.class);
	}
	
}
