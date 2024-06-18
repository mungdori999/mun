package com.mungdori.mun.api.controller.user;

import com.mungdori.mun.api.domain.account.AccountDTO;
import com.mungdori.mun.api.service.user.UserService;
import com.mungdori.mun.security.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JWTUtil jwtUtil;

    @PostMapping("/api/user/signup")
    public ResponseEntity<String> signUp(@RequestBody AccountDTO accountDTO) {
        userService.createAccount(accountDTO);
        return new ResponseEntity<>(accountDTO.getName(), HttpStatus.OK);
    }

    @GetMapping("/api/user")
    public ResponseEntity<AccountDTO> userAccount(HttpServletRequest request) {
        String accessToken = request.getHeader("access");

        if (!(accessToken == null)) {
            String email = jwtUtil.getUserEmail(accessToken);
            AccountDTO userInfo = userService.getUserInfo(email);
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }
}
