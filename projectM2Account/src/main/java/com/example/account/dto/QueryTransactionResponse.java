package com.example.account.dto;

import com.example.account.type.TransactionResultType;
import com.example.account.type.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryTransactionResponse {
    private String accountNumber;
    private TransactionType transactionType;
    private TransactionResultType transactionResult;
    private String transactionId;
    private LocalDateTime transactedAt;
    private Long amount;

    public static QueryTransactionResponse fromTransactionDto(TransactionDto transactionDto) {
        return QueryTransactionResponse.builder()
                .amount(transactionDto.getAmount())
                .accountNumber(transactionDto.getAccountNumber())
                .transactionType(transactionDto.getTransactionType())
                .transactionResult(transactionDto.getTransactionResult())
                .transactionId(transactionDto.getTransactionId())
                .transactedAt(transactionDto.getTransactedAt())
                .build();
    }
}
