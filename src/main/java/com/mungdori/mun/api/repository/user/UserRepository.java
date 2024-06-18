package com.mungdori.mun.api.repository.user;

import com.mungdori.mun.api.domain.account.Account;


public interface UserRepository {
    void saveUserAccount(Account account);

    Account findByUser(String email);
}
