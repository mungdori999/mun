package com.mungdori.mun.api.controller.product;

import com.mungdori.mun.api.domain.product.CartDTO;
import com.mungdori.mun.api.domain.product.ProductDTO;
import com.mungdori.mun.api.service.product.ProductService;
import com.mungdori.mun.security.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final JWTUtil jwtUtil;
    @GetMapping("/api/products")
    public ResponseEntity<List<ProductDTO>> productList() {
        List<ProductDTO> productList = productService.getAllProductList();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/api/products/cart")
    public ResponseEntity<List<CartDTO>> cartList(HttpServletRequest request) {
        String access = request.getHeader("access");
        Long userId = jwtUtil.getUserId(access);
        List<CartDTO> cartList = productService.getAllCartList(userId);
        return new ResponseEntity<>(cartList,HttpStatus.OK);
    }

    @PostMapping("/api/products/cart")
    public ResponseEntity<List<CartDTO>> addCart(HttpServletRequest request,@RequestBody  CartDTO cartDTO) {
        String access = request.getHeader("access");
        Long userId = jwtUtil.getUserId(access);
        cartDTO.setAccountId(userId);
        productService.settingCart(cartDTO);
        List<CartDTO> cartList = productService.getAllCartList(userId);
        return new ResponseEntity<>(cartList,HttpStatus.OK);
    }
}
