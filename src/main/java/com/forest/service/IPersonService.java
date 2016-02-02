package com.forest.service;

import org.springframework.transaction.annotation.Transactional;

import com.forest.entity.Person;

public interface IPersonService extends IService<Person> {
	
	@Transactional(readOnly = true)
	Person findUserByEmail(String email);
}
