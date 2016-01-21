package com.forest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forest.entity.Product;

public class ShoppingCart implements Serializable{

	private static final long serialVersionUID = 8688300881628848111L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCart.class);
	
	
	private List<Product> cartItems;
	
	
	public void addItem(final Product p) {

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        LOGGER.info("Adding product {}", p.getName());
        LOGGER.info("Cart Size: {}", cartItems.size());

        if (!cartItems.contains(p)) {
            cartItems.add(p);
        }
    }
	
	public double getTotal() {
        if (cartItems == null || cartItems.isEmpty()) {
            return 0f;
        }

        double total = 0f;
        for (Product item : cartItems) {
            total += item.getPrice();
        }
        LOGGER.info("Actual Total:{}", total);
        return total;
    }
	
	public List<Product> getCartItems() {
        return cartItems;
    }
}
