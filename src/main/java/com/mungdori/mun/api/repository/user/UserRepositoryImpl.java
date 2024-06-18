package com.mungdori.mun.api.repository.user;

import com.mungdori.mun.api.domain.account.Account;
import com.mungdori.mun.api.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final UserMapper userMapper;

    @Override
    public void saveUserAccount(Account account) {
            userMapper.insertUserAccount(account);
    }

    @Override
    public Account findByUser(String email) {
        return userMapper.selectByUser(email);
    }
}
