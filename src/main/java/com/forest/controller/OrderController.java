package com.forest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.forest.service.IOrderService;

@Controller
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private IOrderService						orderService;
	
	@RequestMapping(value = "orders", method = RequestMethod.GET)
    public String products(Model model) {
        model.addAttribute("myOrders", orderService.getMyOrders());
        return "order/myOrders";
    }
}
