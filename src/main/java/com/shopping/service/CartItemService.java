package com.shopping.service;

import com.shopping.exception.CartItemException;
import com.shopping.exception.UserException;
import com.shopping.model.Cart;
import com.shopping.model.CartItem;
import com.shopping.model.Product;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem)throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId)throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId)throws CartItemException;
}
