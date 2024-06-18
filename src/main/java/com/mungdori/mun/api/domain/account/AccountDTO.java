package com.mungdori.mun.api.domain.account;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
}
