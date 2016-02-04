package com.forest.service;

import java.util.List;

import com.forest.entity.CustomerOrder;

public interface IOrderService extends IService<CustomerOrder> {
	List<CustomerOrder> getMyOrders(Integer id);
}
