package com.mungdori.mun.api.service.user;

import com.mungdori.mun.api.domain.account.Account;
import com.mungdori.mun.api.domain.account.AccountDTO;
import com.mungdori.mun.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public void createAccount(AccountDTO accountDTO) {

        Account account = Account.builder().name(accountDTO.getName())
                .email(accountDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(accountDTO.getPassword()))
                .build();

        userRepository.saveUserAccount(account);
    }

    @Transactional(readOnly = true)
    public AccountDTO getUserInfo(String email) {
        Account account = userRepository.findByUser(email);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setName(account.getName());
        accountDTO.setItemId(account.getItemId());
        return accountDTO;
    }
}
