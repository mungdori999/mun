package com.mungdori.mun.api.repository.product;

import com.mungdori.mun.api.domain.product.Cart;
import com.mungdori.mun.api.domain.product.Product;

import java.util.List;


public interface ProductRepository {

    List<Product> findAllProduct();

    List<Cart> findAllCartByUserId(Long userId);

    void deleteCart(Long userId);

    void saveCart(Cart cartDTO);
}
