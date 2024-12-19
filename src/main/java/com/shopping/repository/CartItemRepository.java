package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopping.model.Cart;
import com.shopping.model.CartItem;
import com.shopping.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

	@Query("SELECT ci FROM CartItem ci WHERE ci.cart = :cart AND ci.product = :product AND ci.size = :size AND ci.userId = :userId")
	public CartItem isCartItemExist(@Param("cart")Cart cart, @Param("product")Product product,@Param("size")String size,
			@Param("userId")Long userid);
}
