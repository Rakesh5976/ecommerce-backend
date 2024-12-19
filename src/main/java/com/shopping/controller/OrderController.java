package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.OrderException;
import com.shopping.exception.UserException;
import com.shopping.model.Address;
import com.shopping.model.Order;
import com.shopping.model.User;
import com.shopping.service.OrderService;
import com.shopping.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<Order>createOrder(@RequestBody Address shippingAddress,
			@RequestHeader("Authorization")String jwt)throws UserException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		Order order=orderService.createOrder(user, shippingAddress);
		
		return new ResponseEntity<Order>(order,HttpStatus.CREATED);
	}
	@GetMapping("/user")
	public ResponseEntity<List<Order>>usersOrderHistory(@RequestHeader("Authorization")String jwt)throws UserException {
		User user=userService.findUserProfileByJwt(jwt);
		List<Order> orders=orderService.usersOrderHistory(user.getId());
		
		return new ResponseEntity<>(orders,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order>findOrderById(@PathVariable("id")Long orderId,
			@RequestHeader("Authorization")String jwt)throws UserException,OrderException {
		
		User user=userService.findUserProfileByJwt(jwt);
		
		Order order = orderService.findOrderById(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
	}
	
}
