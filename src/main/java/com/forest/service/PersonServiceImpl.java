package com.forest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forest.entity.Person;
import com.forest.repository.IPersonRepository;

@Service
public class PersonServiceImpl extends AbstractService<Person> implements IPersonService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Autowired
	private IPersonRepository repository;

	@Override
	public IPersonRepository getRepository() {
		return repository;
	}

	@Override
	public Person findUserByEmail(String email) {
		return getRepository().findUserByEmail(email);
	}
}
