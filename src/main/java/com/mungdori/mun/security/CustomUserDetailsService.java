package com.mungdori.mun.security;

import com.mungdori.mun.api.domain.account.Account;
import com.mungdori.mun.api.repository.user.UserRepository;
import com.mungdori.mun.security.domain.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //DB에서 조회
        Account userData = userRepository.findByUser(username);
        if (userData != null) {
            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
