package com.forest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.forest.entity.Category;
import com.forest.entity.CustomerOrder;
import com.forest.entity.Person;
import com.forest.service.IOrderService;
import com.forest.service.IPersonService;
import com.forest.service.UserService.User;

@Controller
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private IOrderService						orderService;
	
	@Autowired
	private IPersonService						personService;
	
	@RequestMapping(value = "orders", method = RequestMethod.GET)
    public String findOrders(@AuthenticationPrincipal User activeUser, Model model) {
		if (activeUser == null){
			
			// JsfUtil.addErrorMessage("Current user is not authenticated. Please do login before accessing your orders.");
			return "welcome";
		}
		Person person = personService.findUserByEmail(activeUser.getUsername());
		if (person == null){
			// JsfUtil.addErrorMessage("Current user is not authenticated. Please do login before accessing your orders.");
			return "welcome";
		}
		List<CustomerOrder> customerOrders = orderService.getMyOrders(person.getId());
		if (customerOrders.isEmpty()){
//			logger.log(Level.FINEST, "Customer {0} has no orders to display.", user.getEmail());
			
		}
		
        model.addAttribute("myOrders", customerOrders);
        return "order/myOrders";
    }
	
	
	@RequestMapping(value = "/admin/order/list", method = RequestMethod.GET)
    public String adminOrderList(Model model) {
        model.addAttribute("items", orderService.getItems());
        model.addAttribute("pagination", orderService.getPagination());
        return "/admin/order/list";
    }
	
	@RequestMapping(value = "admin/order/previous", method = RequestMethod.GET)
    public String adminOrderPrevious(Model model) {
		if (orderService.getPagination().isHasPreviousPage()){
			orderService.getPagination().previousPage();
		}else{
			LOGGER.warn("Pagination has no previous page. Wrong UI logic!");
		}
		model.addAttribute("items", orderService.getItems());
        model.addAttribute("pagination", orderService.getPagination());
        return "/admin/order/list";
    }
	
	@RequestMapping(value = "admin/order/next", method = RequestMethod.GET)
    public String adminOrderNext(Model model) {
		if (orderService.getPagination().isHasNextPage()){
			orderService.getPagination().nextPage();
		}else{
			LOGGER.warn("Pagination has no next page. Wrong UI logic!");
		}
		model.addAttribute("items", orderService.getItems());
        model.addAttribute("pagination", orderService.getPagination());
        return "/admin/order/list";
    }
	
		
	@RequestMapping(value = "/admin/order/{id}/view", method = RequestMethod.GET)
    public String adminOrderView(@PathVariable("id") int id, Model model) {
		CustomerOrder order = orderService.findById(id);
        model.addAttribute(order);
        return "/admin/order/view";
    }
	
	@RequestMapping(value = "/admin/order/update", method = RequestMethod.DELETE)
    public String adminOrderDelete(@RequestParam("id") Integer id, Model model) {
		CustomerOrder order = orderService.findById(id);
		orderService.delete(order);
		return "redirect:" + "/admin/order/list";
    }
	
	@RequestMapping(value = "/admin/order/update", method = RequestMethod.PUT)
    public String adminOrderCancel(@RequestParam("id") Integer id, Model model) {
		CustomerOrder order = orderService.findById(id);
		model.addAttribute("items", orderService.getItems());
        model.addAttribute("pagination", orderService.getPagination());
        return "/admin/order/list";
    }
	
	@RequestMapping(value = "/admin/order/{id}/delete", method = RequestMethod.GET)
    public String adminOrderDelete(@PathVariable("id") int id, Model model) {
		CustomerOrder order = orderService.findById(id);
		orderService.delete(order);
        return "redirect:" + "/admin/order/list";
    }
}
