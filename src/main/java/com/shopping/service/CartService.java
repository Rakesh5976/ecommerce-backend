package com.shopping.service;

import com.shopping.exception.ProductException;
import com.shopping.model.Cart;
import com.shopping.model.User;
import com.shopping.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req)throws ProductException;
	
	public Cart findUserCart(Long userId);
}
