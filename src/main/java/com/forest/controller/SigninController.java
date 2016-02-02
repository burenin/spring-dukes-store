package com.forest.controller;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forest.service.UserService;
import com.forest.service.UserService.User;

@Controller
public class SigninController {
	
	@RequestMapping(value = "signin")
	public String signin() {
        return "signin/signin";
    }
	
	
	@RequestMapping(value = "signin/success")
	public String signinSuccess(Principal principal) {
		String page = "welcome";
		if ((principal != null) && ( (principal instanceof UsernamePasswordAuthenticationToken))) {
			UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.class.cast(principal);
			User user = User.class.cast(token.getPrincipal());
			if (user.isAdmin()){
				page = "admin/index";
			}
		}
        return page;
    }
}
