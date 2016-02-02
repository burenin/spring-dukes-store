package com.forest.controller;

import java.security.Principal;

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
		String page = "/";
		if ((principal != null) && ( (principal instanceof UserService.User))) {
			User user = User.class.cast(principal);
			if (user.isAdmin()){
				page = "admin/index";
			}
		}
        return page;
    }
}
