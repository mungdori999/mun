package com.mungdori.mun.api.repository.product;

import com.mungdori.mun.api.domain.product.Cart;
import com.mungdori.mun.api.domain.product.Product;
import com.mungdori.mun.api.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository{
    private final ProductMapper productMapper;
    @Override
    public List<Product> findAllProduct() {
        return productMapper.selectAllProduct();

    }

    @Override
    public List<Cart> findAllCartByUserId(Long userId) {
        return productMapper.selectAllCartByUserId(userId);
    }

    @Override
    public void deleteCart(Long cartId) {
        productMapper.deleteCart(cartId);
    }

    @Override
    public void saveCart(Cart cart) {
        productMapper.insertCart(cart);
    }
}
