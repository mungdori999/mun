package com.mungdori.mun.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mungdori.mun.api.domain.account.Account;
import com.mungdori.mun.api.domain.account.AccountDTO;
import com.mungdori.mun.security.domain.CustomUserDetails;
import com.mungdori.mun.security.domain.RefreshToken;
import com.mungdori.mun.security.jwt.RefreshTokenRepository;
import com.mungdori.mun.security.jwt.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;


public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    //JWTUtil 주입
    private final JWTUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    private final AntPathRequestMatcher requestMatcher;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, RefreshTokenRepository refreshTokenRepository) {
        this.requestMatcher = new AntPathRequestMatcher("/api/login", "POST");
        setRequiresAuthenticationRequestMatcher(this.requestMatcher);
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        //클라이언트 요청에서 email, password 추출

        try {
           AccountDTO accountDTO = new ObjectMapper().readValue(request.getInputStream(), AccountDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            accountDTO.getEmail(),
                            accountDTO.getPassword(),
                            null));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        //유저 정보

        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        Account account = principal.getAccount();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();
        
        //토큰 생성
        String access = jwtUtil.createJwt("access", account.getId(), account.getEmail(), role, 600000L);
        String refresh = jwtUtil.createJwt("refresh", account.getId(), account.getEmail() ,role, 86400000L);

        //Refresh 토큰 저장
        addRefreshEntity(refresh, account.getId());

        //응답 설정
        response.setHeader("access", access);
//        response.setHeader("refresh", refresh);
        response.addCookie(createCookie("refresh", refresh));
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        //로그인 실패시 401 응답 코드 반환
        response.setStatus(401);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    private void addRefreshEntity(String refresh,Long id) {
        RefreshToken refreshToken = new RefreshToken(refresh,id);
        refreshTokenRepository.save(refreshToken);
    }
}
