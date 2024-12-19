package com.shopping.service;

import java.util.List;

import com.shopping.exception.ProductException;
import com.shopping.model.Rating;
import com.shopping.model.User;
import com.shopping.request.RatingRequest;

public interface RatingService {

	public Rating createRating(RatingRequest req,User user)throws ProductException;
	
	public List<Rating>getProductsRating(Long productId);
}
