package com.example.account.dto;

import com.example.account.domain.Transaction;
import com.example.account.type.TransactionResultType;
import com.example.account.type.TransactionType;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private String accountNumber;
    private TransactionType transactionType;
    private TransactionResultType transactionResult;
    private Long amount;
    private Long balanceSnapShot;
    private String transactionId;
    private LocalDateTime transactedAt;

    public static TransactionDto fromEntity(Transaction transaction) {
        return TransactionDto.builder()
                .accountNumber(transaction.getAccount().getAccountNumber())
                .transactionType(transaction.getTransactionType())
                .transactionResult(transaction.getTransactionResult())
                .amount(transaction.getAmount())
                .balanceSnapShot(transaction.getBalanceSnapShot())
                .transactionId(transaction.getTransactionId())
                .transactedAt(transaction.getTransactedAt())
                .build();
    }
}
