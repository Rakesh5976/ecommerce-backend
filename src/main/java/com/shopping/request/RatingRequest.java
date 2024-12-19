package com.shopping.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {

	private Long productid;
	private double rating;
}
