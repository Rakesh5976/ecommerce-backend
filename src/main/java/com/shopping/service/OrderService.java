package com.shopping.service;

import java.util.List;

import com.shopping.exception.OrderException;
import com.shopping.model.Address;
import com.shopping.model.Order;
import com.shopping.model.User;

public interface OrderService {

	public Order createOrder(User user,Address shippingAddress);
	
	public Order findOrderById(Long orderId)throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public Order placeOrder(Long orderId)throws OrderException;
	
	public Order confirmedOrder(Long orderId)throws OrderException;
	
	public Order shippedOrder(Long orderId)throws OrderException;
	
	public Order deliveredOrder(Long orderId)throws OrderException;
	
	public Order cancelledOrder(Long orderId)throws OrderException;
	
	public List<Order>getAllOrders();
	
	public void deleteOrder(Long orderId)throws OrderException;


}
