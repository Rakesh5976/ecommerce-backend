package com.shopping.service;

import java.util.List;

import com.shopping.exception.ProductException;
import com.shopping.model.Review;
import com.shopping.model.User;
import com.shopping.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req, User user)throws ProductException;
	
	public List<Review> getAllReview(Long productId);
}
