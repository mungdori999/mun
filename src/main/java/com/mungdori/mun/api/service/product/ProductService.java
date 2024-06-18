package com.mungdori.mun.api.service.product;

import com.mungdori.mun.api.domain.product.Cart;
import com.mungdori.mun.api.domain.product.CartDTO;
import com.mungdori.mun.api.domain.product.Product;
import com.mungdori.mun.api.domain.product.ProductDTO;
import com.mungdori.mun.api.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProductList() {
        List<Product> productList = productRepository.findAllProduct();

        return productList.stream().map(product -> ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .tags(product.getTags())
                .imgUrl(product.getImgUrl()).build()).collect(Collectors.toList());
    }

    public List<CartDTO> getAllCartList(Long userId) {
        List<Cart> cartList = productRepository.findAllCartByUserId(userId);
        return cartList.stream().map(cart -> new CartDTO(cart.getId(), cart.getAccountId(), cart.getProductId())).collect(Collectors.toList());

    }
    public void settingCart(CartDTO cartDTO) {
        List<Cart> cartList = productRepository.findAllCartByUserId(cartDTO.getAccountId());
        Optional<Cart> matchCart = cartList.stream()
                .filter(cart -> cart.getProductId().equals(cartDTO.getProductId()))
                .findFirst();
        if (matchCart.isPresent()) {
            productRepository.deleteCart(matchCart.get().getId());
        }
        else {
            Cart cart = new Cart();
            cart.setAccountId(cartDTO.getAccountId());
            cart.setProductId(cartDTO.getProductId());
            productRepository.saveCart(cart);
        }


    }
}
