package com.forest.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.forest.bean.ShoppingCart;
import com.forest.entity.Customer;
import com.forest.entity.CustomerOrder;
import com.forest.entity.OrderDetail;
import com.forest.entity.OrderDetailPK;
import com.forest.entity.OrderStatus;
import com.forest.entity.Person;
import com.forest.entity.Product;
import com.forest.event.NewsOrderEvent;
import com.forest.service.EventDispatcherService;
import com.forest.service.ICustomerService;
import com.forest.service.IOrderService;
import com.forest.service.IPersonService;
import com.forest.service.IProductService;
import com.forest.service.UserService.User;

@Controller()
@RequestMapping(value="/cart")
public class ShoppingCartController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);
	
	
	@Autowired
	private IProductService						productService;
	
	@Autowired
	private IOrderService						orderService;
	
	@Autowired
	private ShoppingCart						cart;
	
	@Autowired
	private IPersonService						personService;
	
	
	@Autowired
	private ICustomerService					customerService;
	
	
	@Autowired
	private EventDispatcherService 				eventDispatcherService;
	
	
	
	@Autowired
    private MessageSource messageSource;
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public String addProduct(final HttpServletRequest req) {
		String id = null;
		for (Entry<String, String[]> entry : req.getParameterMap().entrySet()){
			if (entry.getKey().startsWith("id=")){
				id = entry.getKey().substring("id=".length());
			}
		}
        final Integer productId = Integer.valueOf(id);
        Product p = productService.findById(productId);
        cart.addItem(p);
        return "redirect:/products/" + p.getCategory().getId();
    }
	
	@RequestMapping(value="/handle", params= { "clear" }, method = RequestMethod.POST)
    public String clearCart(final HttpServletRequest req) {
		cart.clear();
		String referer = req.getHeader("Referer");
	    return "redirect:"+ referer;
	}
	
	@RequestMapping(value="/handle", params= { "checkout" }, method = RequestMethod.POST)
    public String checkoutCart(@AuthenticationPrincipal User activeUser, final HttpServletRequest req, final Locale locale, final Model model) {
		
		if (activeUser == null) {
//            JsfUtil.addErrorMessage(JsfUtil.getStringFromBundle("bundles.Bundle", "LoginBeforeCheckout"));

        } else {
        	if (activeUser.isAdmin()){
//        		JsfUtil.addErrorMessage(JsfUtil.getStringFromBundle("bundles.Bundle", "AdministratorNotAllowed"));
                
        	}else{
        		
        		Person person = personService.findUserByEmail(activeUser.getUsername());
        		if (person == null){
        			// JsfUtil.addErrorMessage("Current user is not authenticated. Please do login before accessing your orders.");
        		}else{
        			Customer customer = customerService.findById(person.getId());
        			CustomerOrder order = new CustomerOrder();
        			List<OrderDetail> details = new ArrayList<>();
        			
        			OrderStatus orderStatus = new OrderStatus();
        			orderStatus.setId(1); //by default the initial status
        			
        			order.setDateCreated(Calendar.getInstance().getTime());
        			order.setOrderStatus(orderStatus);
        			order.setAmount(cart.getTotal());
        			order.setCustomer(customer);
        			
        			order = orderService.save(order);
        			
        			for (Product p : cart.getCartItems()) {
        				OrderDetail detail = new OrderDetail();
        				
        				OrderDetailPK pk = new OrderDetailPK(order.getId(), p.getId());
        				//TODO: next version will handle qty on shoppingCart 
        				detail.setQty(1);
        				detail.setProduct(p);
        				//detail.setCustomerOrder(order);
        				detail.setOrderDetailPK(pk);
        				
        				details.add(detail);
        			}
        			
        			order.setOrderDetailList(details);
        			orderService.save(order);
        			
        			NewsOrderEvent event = orderToEvent(order);
        			
        			LOGGER.info("{} Sending event from ShoppingCart", Thread.currentThread().getName());
        			eventDispatcherService.publish(event);
        			
        			String message = messageSource.getMessage("Cart_Checkout_Success", null, locale);
        			model.addAttribute("successInfo", message);
        			cart.clear();
        		}
        		
        		
        		
        		
        	}
            
            
        }
		return "welcome";
	}
	
	private NewsOrderEvent orderToEvent(CustomerOrder order) {
		NewsOrderEvent event = new NewsOrderEvent();

        event.setAmount(order.getAmount());
        event.setCustomerID(order.getCustomer().getId());
        event.setDateCreated(order.getDateCreated());
        event.setStatusID(order.getOrderStatus().getId());
        event.setOrderID(order.getId());

        return event;
    }
}
