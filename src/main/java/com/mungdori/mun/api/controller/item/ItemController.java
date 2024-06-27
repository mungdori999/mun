package com.mungdori.mun.api.controller.item;

import com.mungdori.mun.api.domain.item.ItemDTO;
import com.mungdori.mun.api.service.item.GageService;
import com.mungdori.mun.api.service.item.ItemService;
import com.mungdori.mun.security.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final GageService gageService;
    private final JWTUtil jwtUtil;
    @PatchMapping("/api/item/starting")
    public ResponseEntity<String> startItem(HttpServletRequest request) {
        String access = request.getHeader("access");
        Long userId = jwtUtil.getUserId(access);
        Long itemId = itemService.setStartingItem(userId);
        gageService.setStartGage(userId,itemId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/api/item/{itemId}")
    public ResponseEntity<ItemDTO> item(@PathVariable("itemId") Long itemId) {
        ItemDTO item = itemService.getItem(itemId);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }
}
