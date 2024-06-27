package com.mungdori.mun.api.mapper;

import com.mungdori.mun.api.domain.product.Cart;
import com.mungdori.mun.api.domain.product.CartDTO;
import com.mungdori.mun.api.domain.product.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    void insertProduct(Product product);
    List<Product> selectAllProduct();
    List<CartDTO> selectAllCartByUserId(@Param("userId") Long userId);

    void insertCart(Cart cart);

    void deleteCart(@Param("cartId") Long cartId);


}
