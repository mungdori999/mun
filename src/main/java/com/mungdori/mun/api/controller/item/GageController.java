package com.mungdori.mun.api.controller.item;

import com.mungdori.mun.api.domain.item.TimeGageDTO;
import com.mungdori.mun.api.service.item.GageService;
import com.mungdori.mun.security.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GageController {

    private final JWTUtil jwtUtil;
    private final GageService gageService;

    @GetMapping("/api/gage")
    public ResponseEntity<TimeGageDTO> gage(HttpServletRequest request) {
        String access = request.getHeader("access");
        Long userId = jwtUtil.getUserId(access);
        TimeGageDTO timeGage = gageService.getGage(userId);
        return new ResponseEntity<>(timeGage, HttpStatus.OK);
    }
}
