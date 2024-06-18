package com.mungdori.mun.api.mapper;

import com.mungdori.mun.api.domain.product.Cart;
import com.mungdori.mun.api.domain.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProductMapperTest {


    @Autowired
    private ProductMapper productMapper;

    @Test
    void insertProduct() {

        List<String> tags = new ArrayList<>();
        tags.add("dog");
        tags.add("cat");

        Product product = Product.builder()
                .name("C")
                .price("40")
                .tags(tags)
                .imgUrl("https://via.placeholder.com/150")
                .build();

        productMapper.insertProduct(product);
    }

    @Test
    void getProduct() {

        productMapper.selectAllProduct();
    }

    @Test
    void saveCart() {

        Cart cart = new Cart();
        cart.setProductId(3L);
        cart.setAccountId(6L);
        productMapper.insertCart(cart);
    }
}