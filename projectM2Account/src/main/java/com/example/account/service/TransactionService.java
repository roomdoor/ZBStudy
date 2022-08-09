package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import com.example.account.domain.Transaction;
import com.example.account.dto.TransactionDto;
import com.example.account.exceoption.AccountException;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.AccountUserRepository;
import com.example.account.repository.TransactionRepository;
import com.example.account.type.AccountStatus;
import com.example.account.type.ErrorCode;
import com.example.account.type.TransactionResultType;
import com.example.account.type.TransactionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.example.account.type.TransactionResultType.*;
import static com.example.account.type.TransactionType.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountUserRepository accountUserRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public TransactionDto useBalance(Long id, String accountNumber, Long amount) {
        AccountUser user = accountUserRepository.findById(id).orElseThrow(
                () -> new AccountException(ErrorCode.USER_NOT_FOUND));
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(
                () -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));

        validationUseBalance(user, account, amount);

        account.useBalance(amount);

        return TransactionDto.fromEntity(
                saveAndGetTransaction(USE, amount, account, S)
        );
    }

    private void validationUseBalance(AccountUser user, Account account, Long amount) {
        if (!Objects.equals(user.getId(), account.getId())) {
            throw new AccountException(ErrorCode.USER_ACCOUNT_UN_MATCH);
        }

        if (account.getAccountStatus() != AccountStatus.IN_USE) {
            throw new AccountException(ErrorCode.ACCOUNT_ALREADY_UNREGISTERED);
        }

        if (amount > account.getBalance()) {
            throw new AccountException(ErrorCode.AMOUNT_EXCEED_BALANCE);
        }
    }

    @Transactional
    public void saveFailedUseTransaction(String accountNumber, Long amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));

        saveAndGetTransaction(USE, amount, account, F);
    }

    private Transaction saveAndGetTransaction(
            TransactionType transactionType,
            Long amount,
            Account account,
            TransactionResultType r
    ) {
        return transactionRepository.save(
                Transaction.builder()
                        .transactionType(transactionType)
                        .transactionResult(r)
                        .account(account)
                        .amount(amount)
                        .balanceSnapShot(account.getBalance())
                        .transactionId(UUID.randomUUID().toString().replaceAll("-", ""))
                        .transactedAt(LocalDateTime.now())
                        .build()
        );
    }

    @Transactional
    public TransactionDto cancelBalance(String transactionId, String accountNumber, Long amount) {
        Transaction transaction = transactionRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new AccountException(ErrorCode.TRANSACTION_NOT_FOUNT));
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(
                () -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));

        validationCancelBalance(transaction, account, amount);

        account.cancelBalance(amount);

        return TransactionDto.fromEntity(
                saveAndGetTransaction(CANCEL, amount, account, S)
        );
    }

    private void validationCancelBalance(Transaction transaction, Account account, Long amount) {
        if (!Objects.equals(transaction.getAccount().getId(), account.getId())) {
            throw new AccountException(ErrorCode.TRANSACTION_ACCOUNT_UN_MATCH);
        }

        if (!Objects.equals(transaction.getAmount(), amount)) {
            throw new AccountException(ErrorCode.CANCEL_MUST_FULLY);
        }
    }

    @Transactional
    public void saveFailedCancelTransaction(String accountNumber, Long amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));

        saveAndGetTransaction(CANCEL, amount, account, F);
    }

    @Transactional
    public TransactionDto queryTransaction(String transactionId) {
        Transaction transaction = transactionRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new AccountException(ErrorCode.TRANSACTION_NOT_FOUNT));

        return TransactionDto.fromEntity(transaction);
    }
}
