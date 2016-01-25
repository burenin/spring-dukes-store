package com.forest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.forest.entity.Customer;
import com.forest.service.ICustomerService;

@Controller
public class CustomerController {

	@Autowired
	private ICustomerService service;
	
	@RequestMapping(value = "/customer/new", method = RequestMethod.GET)
    public String initCreationForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute(customer);
        return "customer/newForm";
    }
}
