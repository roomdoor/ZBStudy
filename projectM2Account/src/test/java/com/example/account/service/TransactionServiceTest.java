package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import com.example.account.domain.Transaction;
import com.example.account.dto.TransactionDto;
import com.example.account.exceoption.AccountException;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.AccountUserRepository;
import com.example.account.repository.TransactionRepository;
import com.example.account.type.ErrorCode;
import com.example.account.type.TransactionResultType;
import com.example.account.type.TransactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.account.type.AccountStatus.*;
import static com.example.account.type.TransactionResultType.*;
import static com.example.account.type.TransactionResultType.S;
import static com.example.account.type.TransactionType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountUserRepository accountUserRepository;
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionService transactionService;

    @DisplayName("01. use balance success")
    @Test
    public void test_01() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(1L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));

        Account account = Account.builder()
                .accountStatus(IN_USE)
                .accountNumber("1234512345")
                .balance(10000L)
                .build();
        account.setId(1L);
        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));

        given(transactionRepository.save(any()))
                .willReturn(Transaction.builder()
                        .transactionType(USE)
                        .transactionResult(S)
                        .balanceSnapShot(9000L)
                        .account(account)
                        .amount(1000L)
                        .build());
        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);

        //when
        TransactionDto transactionDto = transactionService.useBalance(
                1L, "1234512345", 2000L);

        //then
        verify(transactionRepository, times(1)).save(captor.capture());
        assertEquals(captor.getValue().getAmount(), 2000L);
        assertEquals(captor.getValue().getAccount().getAccountNumber(), "1234512345");
        assertEquals(captor.getValue().getBalanceSnapShot(), 8000L);
        assertEquals(captor.getValue().getTransactionResult(), S);
        assertEquals(captor.getValue().getTransactionType(), USE);
        assertEquals(S, transactionDto.getTransactionResult());
        assertEquals(USE, transactionDto.getTransactionType());
        assertEquals(9000, transactionDto.getBalanceSnapShot());
        assertEquals(1000, transactionDto.getAmount());
    }

    @DisplayName("02. use balance not fount user")
    @Test
    public void test_02() {
        //given
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> transactionService.useBalance(
                        1L, "1234512345", 2000L));
        //then
        assertEquals(ErrorCode.USER_NOT_FOUND, e.getErrorCode());
    }

    @DisplayName("03. use balance not found account")
    @Test
    public void test_03() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(12L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));
        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.empty());

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> transactionService.useBalance(
                        1L, "1234512345", 2000L));
        //then
        assertEquals(ErrorCode.ACCOUNT_NOT_FOUND, e.getErrorCode());
    }

    @DisplayName("04. use balance un matched user id")
    @Test
    public void test_04() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(1L);
        AccountUser accountUser1 = AccountUser.builder()
                .name("Bee").build();
        accountUser.setId(2L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));

        Account account = Account.builder()
                .accountUser(accountUser1)
                .accountNumber("1234512345")
                .balance(10000L)
                .build();
        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> transactionService.useBalance(
                        1L, "1234512345", 2000L));
        //then
        assertEquals(ErrorCode.USER_ACCOUNT_UN_MATCH, e.getErrorCode());
    }

    @DisplayName("05 . use balance account status unregistered")
    @Test
    public void test_05() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(1L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));

        Account account = Account.builder()
                .accountStatus(UNREGISTERED)
                .accountNumber("1234512345")
                .balance(10000L)
                .build();
        account.setId(1L);
        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> transactionService.useBalance(
                        1L, "1234512345", 2000L));
        //then
        assertEquals(ErrorCode.ACCOUNT_ALREADY_UNREGISTERED, e.getErrorCode());
    }

    @DisplayName("06 . use balance amount bigger than balance")
    @Test
    public void test_06() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(1L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));

        Account account = Account.builder()
                .accountStatus(IN_USE)
                .accountNumber("1234512345")
                .balance(1000L)
                .build();
        account.setId(1L);
        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> transactionService.useBalance(
                        1L, "1234512345", 2000L));
        //then
        assertEquals(ErrorCode.AMOUNT_EXCEED_BALANCE, e.getErrorCode());
    }

    @DisplayName("07. save failed use transaction success")
    @Test
    public void test_07() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(12L);

        Account account = Account.builder()
                .accountUser(accountUser)
                .accountStatus(IN_USE)
                .accountNumber("1234512345")
                .balance(10000L)
                .build();
        account.setId(12L);
        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));

        given(transactionRepository.save(any()))
                .willReturn(Transaction.builder()
                        .transactionType(USE)
                        .transactionResult(S)
                        .balanceSnapShot(9000L)
                        .account(account)
                        .amount(1000L)
                        .build());
        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);

        //when
        transactionService.saveFailedUseTransaction(
                "1234512345", 2000L);

        //then
        verify(transactionRepository, times(1)).save(captor.capture());
        assertEquals(captor.getValue().getAmount(), 2000L);
        assertEquals(captor.getValue().getAccount().getAccountNumber(), "1234512345");
        assertEquals(captor.getValue().getBalanceSnapShot(), 10000L);
        assertEquals(captor.getValue().getTransactionResult(), F);
        assertEquals(captor.getValue().getTransactionType(), USE);
    }

    @DisplayName("08. cancel balance success")
    @Test
    public void test_08() {
        //given
        Account account = Account.builder()
                .accountStatus(IN_USE)
                .accountNumber("1234512345")
                .balance(10000L)
                .build();
        account.setId(1L);

        Transaction transaction = Transaction.builder()
                .transactionId("transactionIdForCancelTest")
                .transactionType(USE)
                .transactionResult(S)
                .balanceSnapShot(10000L)
                .account(account)
                .amount(2000L)
                .build();

        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.of(transaction));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));


        given(transactionRepository.save(any()))
                .willReturn(Transaction.builder()
                        .transactionId("transactionIdForCancelTest")
                        .transactionType(CANCEL)
                        .transactionResult(S)
                        .balanceSnapShot(12000L)
                        .account(account)
                        .amount(2000L)
                        .build());
        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);

        //when
        TransactionDto transactionDto = transactionService.cancelBalance(
                "transactionIdForCancelTest", "1234512345", 2000L);

        //then
        verify(transactionRepository, times(1)).save(captor.capture());
        assertEquals(captor.getValue().getAmount(), 2000L);
        assertEquals(captor.getValue().getAccount().getAccountNumber(), "1234512345");
        assertEquals(captor.getValue().getBalanceSnapShot(), 12000L);
        assertEquals(captor.getValue().getTransactionResult(), S);
        assertEquals(captor.getValue().getTransactionType(), CANCEL);
        assertEquals(S, transactionDto.getTransactionResult());
        assertEquals(CANCEL, transactionDto.getTransactionType());
        assertEquals(12000, transactionDto.getBalanceSnapShot());
        assertEquals(2000, transactionDto.getAmount());
    }

    @DisplayName("09. cancel balance not found transaction")
    @Test
    public void test_09() {
        //given
        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.empty());

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> transactionService.cancelBalance(
                        "transactionIdForCancelTest", "1234512345", 2000L));
        //then
        assertEquals(ErrorCode.TRANSACTION_NOT_FOUNT, e.getErrorCode());
    }

    @DisplayName("10. cancel balance not found account")
    @Test
    public void test_10() {
        //given
        Transaction transaction = Transaction.builder()
                .transactionId("transactionIdForCancelTest")
                .transactionType(USE)
                .transactionResult(S)
                .balanceSnapShot(10000L)
                .amount(2000L)
                .build();

        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.of(transaction));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.empty());

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> transactionService.cancelBalance(
                        "transactionIdForCancelTest", "1234512345", 2000L));
        //then
        assertEquals(ErrorCode.ACCOUNT_NOT_FOUND, e.getErrorCode());
    }

    @DisplayName("11. cancel balance transaction account um matched")
    @Test
    public void test_11() {
        //given
        Account account = Account.builder()
                .accountStatus(IN_USE)
                .accountNumber("1234512345")
                .balance(10000L)
                .build();
        account.setId(1L);

        Account account1 = Account.builder()
                .accountStatus(IN_USE)
                .accountNumber("1234512345")
                .balance(10000L)
                .build();
        account1.setId(2L);

        Transaction transaction = Transaction.builder()
                .transactionId("transactionIdForCancelTest")
                .transactionType(USE)
                .transactionResult(S)
                .balanceSnapShot(10000L)
                .account(account1)
                .amount(2000L)
                .build();

        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.of(transaction));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));
        //when
        AccountException e = assertThrows(AccountException.class,
                () -> transactionService.cancelBalance(
                        "transactionIdForCancelTest", "1234512345", 2000L));
        //then
        assertEquals(ErrorCode.TRANSACTION_ACCOUNT_UN_MATCH, e.getErrorCode());
    }

    @DisplayName("12. cancel balance amount um matched")
    @Test
    public void test_12() {
        //given
        Account account = Account.builder()
                .accountStatus(IN_USE)
                .accountNumber("1234512345")
                .balance(10000L)
                .build();
        account.setId(1L);

        Transaction transaction = Transaction.builder()
                .transactionId("transactionIdForCancelTest")
                .transactionType(USE)
                .transactionResult(S)
                .balanceSnapShot(10000L)
                .account(account)
                .amount(2000L)
                .build();

        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.of(transaction));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(account));
        //when
        AccountException e = assertThrows(AccountException.class,
                () -> transactionService.cancelBalance(
                        "transactionIdForCancelTest", "1234512345", 1000L));
        //then
        assertEquals(ErrorCode.CANCEL_MUST_FULLY, e.getErrorCode());
    }

    @DisplayName("13. query transaction success")
    @Test
    public void test_13() {
        //given
        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.of(Transaction.builder()
                        .account(Account.builder()
                                .accountNumber("1234512345")
                                .build())
                        .transactionType(USE)
                        .transactionResult(S)
                        .amount(100L)
                        .balanceSnapShot(900L)
                        .transactionId("queryTransaction")
                        .transactedAt(LocalDateTime.now())
                        .build()));
        //when
        TransactionDto transactionDto = transactionService.queryTransaction("queryTransaction");

        //then
        assertEquals("1234512345", transactionDto.getAccountNumber());
        assertEquals(100L, transactionDto.getAmount());
        assertEquals(900L, transactionDto.getBalanceSnapShot());
        assertEquals("queryTransaction", transactionDto.getTransactionId());
    }

    @DisplayName("14. query transaction not found transaction")
    @Test
    public void test_14() {
        //given
        given(transactionRepository.findByTransactionId(anyString()))
                .willReturn(Optional.empty());
        //when
        AccountException e = assertThrows(AccountException.class,
                () -> transactionService.queryTransaction("queryTransaction"));

        //then
        assertEquals(ErrorCode.TRANSACTION_NOT_FOUNT, e.getErrorCode());

    }
}