package com.forest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.forest.entity.Person;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private IPersonService personService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = personService.findUserByEmail(username);
		if (person == null){
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(person);
	}
	
	public void signin(Person person) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(person));
	}
	
	private User createUser(Person person) {
		return new User(person);
	}
	
	private Authentication authenticate(Person person) {
		return new UsernamePasswordAuthenticationToken(createUser(person), null, person.getAuthorities());
	}
	
	public static class User extends org.springframework.security.core.userdetails.User {

		private static final long serialVersionUID = 7551786221146601323L;
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
