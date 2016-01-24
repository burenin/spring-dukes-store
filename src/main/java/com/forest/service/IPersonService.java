package com.forest.service;

import com.forest.entity.Person;

public interface IPersonService extends IService<Person> {
	
	Person findUserByEmail(String email);
}
