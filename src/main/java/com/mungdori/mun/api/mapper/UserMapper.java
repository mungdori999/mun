package com.mungdori.mun.api.mapper;

import com.mungdori.mun.api.domain.account.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void insertUserAccount(Account account);

    Account selectByUser(@Param("email") String email);
}
