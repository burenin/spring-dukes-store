package com.forest.repository.jpa;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.forest.entity.Person;
import com.forest.repository.IPersonRepository;

@Repository
public class JpaPersonRepository extends AbstractRepository<Person> implements IPersonRepository {

	public JpaPersonRepository() {
		super(Person.class);
	}
	
	@Override
	public Person findUserByEmail(String email) {
        Query createNamedQuery = getEntityManager().createNamedQuery("Person.findByEmail");

        createNamedQuery.setParameter("email", email);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Person) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }
    }
}
