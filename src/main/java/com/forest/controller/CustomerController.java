package com.forest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forest.controller.support.MessageHelper;
import com.forest.entity.Customer;
import com.forest.service.ICustomerService;
import com.forest.service.UserService;

@Controller
public class CustomerController {
	
	private static final String CUSTOMER_NEW_VIEW_NAME = "customer/newForm";

	@Autowired
	private ICustomerService service;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/customer/new", method = RequestMethod.GET)
    public String initCreationForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute(customer);
        return CUSTOMER_NEW_VIEW_NAME;
    }
	
	@RequestMapping(value = "/customer/new", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute Customer customer, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return CUSTOMER_NEW_VIEW_NAME;
		}
		customer = service.save(customer);
		userService.signin(customer);
        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
        MessageHelper.addSuccessAttribute(ra, "CustomerCreated");
		return "redirect:/";
	}
}
