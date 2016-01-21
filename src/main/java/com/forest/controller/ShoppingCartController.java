package com.forest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forest.bean.ShoppingCart;
import com.forest.entity.Product;
import com.forest.service.IProductService;

@Controller
public class ShoppingCartController {
	
	@Autowired
	private IProductService						productService;
	
	@Autowired
	private ShoppingCart						cart;
	
	@RequestMapping(value="/cart", params={"add"})
    public String addProduct(final HttpServletRequest req) {
        final Integer productId = Integer.valueOf(req.getParameter("add"));
        Product p = productService.findById(productId);
        cart.addItem(p);
        return "redirect:/products/" + p.getCategory().getId();
    }
}
