package com.shopping.service;

import org.springframework.stereotype.Service;

import com.shopping.exception.ProductException;
import com.shopping.model.Cart;
import com.shopping.model.CartItem;
import com.shopping.model.Product;
import com.shopping.model.User;
import com.shopping.repository.CartRepository;
import com.shopping.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService {
	
	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private ProductService productService;
	
	public CartServiceImplementation(CartRepository cartRepository,
			CartItemService cartItemService,
			ProductService productService) {
		this.cartItemService=cartItemService;
		this.cartRepository=cartRepository;
		this.productService=productService;
	}
	

	@Override
	public Cart createCart(User user) {
		Cart cart=new Cart();
		cart.setUser(user);
		
		return cartRepository.save(cart);
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		Cart cart=cartRepository.findByUserId(userId);
		
		Product product=productService.findProductById(req.getProductId());
		
		CartItem isPresent=cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
		
		if(isPresent==null) {
			CartItem cartItem=new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			
			int price=req.getQuantity()*product.getDiscountPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize()); 
			
			CartItem createdCartItem=cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
			
		}
		
		return "item Add to Cart";
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart=cartRepository.findByUserId(userId);
		
		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		
		for(CartItem cartItem:cart.getCartItems()) {
			totalPrice=totalPrice+cartItem.getPrice();
			totalDiscountedPrice=totalDiscountedPrice+cartItem.getDiscountPrice();
			totalItem=totalItem+cartItem.getQuantity();
		}
		
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalItem(totalItem);
		cart.setTotalPrice(totalPrice);
		cart.setDiscounte(totalPrice-totalDiscountedPrice);
		return cartRepository.save(cart);
	}

}
