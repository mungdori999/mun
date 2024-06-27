package com.mungdori.mun.api.domain.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class Account {

    private Long id;
    private String name;
    private String email;
    private String password;

    private String role;
    private Long itemId;
}
