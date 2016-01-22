package com.forest.controller;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forest.bean.ShoppingCart;
import com.forest.entity.Product;
import com.forest.service.IProductService;

@Controller()
@RequestMapping(value="/cart")
public class ShoppingCartController {
	
	@Autowired
	private IProductService						productService;
	
	@Autowired
	private ShoppingCart						cart;
	
	@RequestMapping(value="/add")
    public String addProduct(final HttpServletRequest req) {
		String id = null;
		for (Entry<String, String[]> entry : req.getParameterMap().entrySet()){
			if (entry.getKey().startsWith("id=")){
				String[] value = entry.getValue();
				id = value[0];
			}
		}
        final Integer productId = Integer.valueOf(id);
        Product p = productService.findById(productId);
        cart.addItem(p);
        return "redirect:/products/" + p.getCategory().getId();
    }
}
