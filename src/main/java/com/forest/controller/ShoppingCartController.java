package com.forest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.forest.bean.ShoppingCart;
import com.forest.entity.Product;
import com.forest.service.IProductService;
//http://richardchesterwood.blogspot.com/2011/03/using-sessions-in-spring-mvc-including.html
@Controller
@SessionAttributes("cart")
public class ShoppingCartController {
	
	@Autowired
	private IProductService						productService;
	
	
	@ModelAttribute("cart")
    public ShoppingCart initShoppingCart() {
		ShoppingCart cart = new ShoppingCart();
		return cart;
    }
	
	
	@RequestMapping(value="/cart", params={"add"})
    public String addProduct(final ShoppingCart cart, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer productId = Integer.valueOf(req.getParameter("add"));
        Product p = productService.findById(productId);
        cart.addItem(p);
        return "redirect:/products/" + p.getCategory().getId();
    }
	
	@RequestMapping(value="/fragments/shoppingCart")
	public void cartItems(final ShoppingCart cart) {
        System.err.println("");
    }
	

}
