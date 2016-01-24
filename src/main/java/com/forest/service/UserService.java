package com.forest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.forest.entity.Person;

public class UserService implements UserDetailsService {
	
	@Autowired
	private IPersonService personService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = personService.findUserByEmail(username);
		if (person == null){
			throw new UsernameNotFoundException("user not found");
		}
		return null;
	}
	
	
	private static class User extends org.springframework.security.core.userdetails.User {

        private final Person person;

        public User(Person person) {
            super(person.getEmail(), person.getPassword(), person.getAuthorities());
            this.person = person;
        }

        public Person getPerson() {
			return person;
		}

		public boolean isAdmin() {
            return getPerson().isAdmin();
        }
    }

}
