package com.forest.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forest.controller.support.MessageHelper;
import com.forest.entity.Category;
import com.forest.entity.Customer;
import com.forest.service.ICustomerService;
import com.forest.service.UserService;

@Controller
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
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
	
	
	@RequestMapping(value = "/admin/customer/list", method = RequestMethod.GET)
    public String adminCustomerList(Model model) {
        model.addAttribute("items", service.getItems());
        model.addAttribute("pagination", service.getPagination());
        return "/admin/customer/list";
    }
	
	@RequestMapping(value = "admin/customer/previous", method = RequestMethod.GET)
    public String adminCustomerPrevious(Model model) {
		if (service.getPagination().isHasPreviousPage()){
			service.getPagination().previousPage();
		}else{
			LOGGER.warn("Pagination has no previous page. Wrong UI logic!");
		}
		model.addAttribute("items", service.getItems());
        model.addAttribute("pagination", service.getPagination());
        return "/admin/customer/list";
    }
	
	@RequestMapping(value = "admin/customer/next", method = RequestMethod.GET)
    public String adminCustomerNext(Model model) {
		if (service.getPagination().isHasNextPage()){
			service.getPagination().nextPage();
		}else{
			LOGGER.warn("Pagination has no next page. Wrong UI logic!");
		}
		model.addAttribute("items", service.getItems());
        model.addAttribute("pagination", service.getPagination());
        return "/admin/customer/list";
    }
	
		
	@RequestMapping(value = "/admin/customer/{id}/view", method = RequestMethod.GET)
    public String adminCustomerView(@PathVariable("id") int id, Model model) {
		Customer customer = service.findById(id);
        model.addAttribute(customer);
        return "/admin/customer/view";
    }
	
	@RequestMapping(value = "admin/customer/{id}/edit", method = RequestMethod.GET)
    public String adminCustomerEdit(@PathVariable("id") int id, Model model) {
		Customer customer = service.findById(id);
        model.addAttribute(customer);
        return "/admin/customer/edit";
    }
	
	@RequestMapping(value = "admin/customer/delete", method = RequestMethod.DELETE)
    public String adminCategoryDelete(@RequestParam("id") Integer id, Model model) {
		Customer customer = service.findById(id);
		service.delete(customer);
		model.addAttribute("items", service.getItems());
        model.addAttribute("pagination", service.getPagination());
        return "/admin/customer/list";
    }
	
	@RequestMapping(value = "/admin/customer/create", method = RequestMethod.GET)
    public String adminCustomerCreate(Model model) {
        model.addAttribute(new Customer());
        return "/admin/customer/create";
    }
}
