package com.forest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forest.controller.OrderController;
import com.forest.entity.CustomerOrder;
import com.forest.repository.IOrderRepository;

@Service
public class OrderServiceImpl extends AbstractService<CustomerOrder> implements IOrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private IOrderRepository repository;
	

	@Override
	public IOrderRepository getRepository() {
		return repository;
	}


	@Override
	public List<CustomerOrder> getMyOrders() {
		
		
		
//		if (user != null) {

            
            
            
//          List<CustomerOrder> myOrders = repository.getOrderByCustomerId(user.getId());
            List<CustomerOrder> myOrders = repository.getOrderByCustomerId(1);
            
    		if (myOrders.isEmpty()) {
//    			LOGGER.debug("Customer {0} has no orders to display.", user.getEmail());
    			LOGGER.debug("Customer {0} has no orders to display.", "");
    		    return null;
    		} else {
    			LOGGER.debug("Order amount:{0}", myOrders.get(0).getAmount());
    		    return myOrders;
    		}

//        } 
//		else {
//
//            JsfUtil.addErrorMessage("Current user is not authenticated. Please do login before accessing your orders.");
//
//            return null;
//        }
	}
	
	

}
