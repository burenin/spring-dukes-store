package com.forest.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SigninController {
	
	@RequestMapping(value = "signin/success")
	public String signinSuccess(Principal principal) {
		principal.
        return "signin/signin";
    }

}
