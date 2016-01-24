package com.forest.repository;

import com.forest.entity.Person;

public interface IPersonRepository extends IRepository<Person> {

	Person findUserByEmail(String email);
}
