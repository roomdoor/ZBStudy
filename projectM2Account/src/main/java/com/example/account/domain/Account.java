package com.example.account.domain;

import javax.persistence.*;

import com.example.account.exceoption.AccountException;
import com.example.account.type.AccountStatus;

import java.time.LocalDateTime;


import com.example.account.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Account extends BaseEntity {
    @ManyToOne
    private AccountUser accountUser;
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private Long balance;

    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;

    public void useBalance(Long amount) {
        if (amount > this.balance) {
            throw new AccountException(ErrorCode.AMOUNT_EXCEED_BALANCE);
        }
        this.balance -= amount;
    }

    public void cancelBalance(Long amount) {
        if (amount < 0) {
            throw new AccountException(ErrorCode.INVALID_REQUEST);
        }
        this.balance += amount;
    }
}
