package com.example.account.domain;


import com.example.account.type.TransactionResultType;
import com.example.account.type.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transaction extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private TransactionResultType transactionResult;

    @ManyToOne
    private Account account;
    private Long amount;
    private Long balanceSnapShot;

    private String transactionId;
    private LocalDateTime transactedAt;
}
