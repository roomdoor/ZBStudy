package com.example.account.dto;


import java.time.LocalDateTime;

import com.example.account.domain.Account;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private Long userId;
    private String accountNumber;
    private Long balance;

    private LocalDateTime registerAt;
    private LocalDateTime unRegisterAt;

    public static AccountDto fromEntity(Account account) {
        return AccountDto.builder()
                .userId(account.getAccountUser().getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .registerAt(account.getRegisteredAt())
                .unRegisterAt(account.getUnRegisteredAt())
                .build();
    }
}
