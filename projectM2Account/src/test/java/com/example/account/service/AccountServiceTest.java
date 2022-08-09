package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import com.example.account.dto.AccountDto;
import com.example.account.exceoption.AccountException;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.AccountUserRepository;
import com.example.account.type.AccountStatus;
import com.example.account.type.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountUserRepository accountUserRepository;

    @Mock
    private Random random;

    @InjectMocks
    private AccountService accountService;

    @DisplayName("01. create account success")
    @Test
    public void test_01() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(12L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));

        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.empty());

        given(accountRepository.save(any()))
                .willReturn(Account.builder()
                        .accountUser(accountUser)
                        .accountNumber("1000000015").build());

        given(random.nextInt(10)).willReturn(1);


        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        //when
        AccountDto accountDto = accountService.createAccount(1L, 10000L);

        //then
        verify(accountRepository, times(1)).save(captor.capture());

        assertEquals(12L, accountDto.getUserId());
    }

    @DisplayName("02. create account user not found")
    @Test
    public void test_02() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(12L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> accountService.createAccount(1L, 10000L));

        //then
        assertEquals(ErrorCode.USER_NOT_FOUND, e.getErrorCode());
    }

    @DisplayName("03. create account maxAccount 10")
    @Test
    public void test_03() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(12L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));
        given(accountRepository.countByAccountUser(accountUser))
                .willReturn(10);

        //when

        AccountException e = assertThrows(AccountException.class,
                () -> accountService.createAccount(1L, 10000L));

        //then
        assertEquals(ErrorCode.MAX_ACCOUNT_PER_USER_10, e.getErrorCode());
    }

    @DisplayName("04. create account random 10")
    @Test
    public void test_04() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(1L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));
        given(accountRepository.findByAccountNumber(any()))
                .willReturn(Optional.empty());
        given(accountRepository.save(any()))
                .willReturn(Account.builder()
                        .accountUser(accountUser)
                        .accountNumber("1000000015").build());

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        //when
        given(random.nextInt(10)).willReturn(0);
        AccountDto accountDto = accountService.createAccount(1L, 10000L);

        given(random.nextInt(10)).willReturn(1);
        AccountDto accountDto2 = accountService.createAccount(1L, 10000L);

        //then
        verify(accountRepository, times(2)).save(captor.capture());

        assertNotEquals(captor.getAllValues().get(0).getAccountNumber()
                , captor.getAllValues().get(1).getAccountNumber());
        assertEquals(1L, accountDto.getUserId());
    }

    @DisplayName("05. delete account success")
    @Test
    public void test_05() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(12L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));

        given(accountRepository.findByAccountNumber(any()))
                .willReturn(Optional.of(Account.builder()
                        .accountUser(accountUser)
                        .accountStatus(AccountStatus.IN_USE)
                        .accountNumber("1234567890")
                        .balance(0L)
                        .build()));

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        //when
        AccountDto accountDto = accountService.deleteAccount(1L, "1234567890");

        //then
        verify(accountRepository, times(1)).save(captor.capture());

        assertEquals(12L, accountDto.getUserId());
        assertEquals("1234567890", captor.getValue().getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED, captor.getValue().getAccountStatus());
    }

    @DisplayName("06. delete account user not found")
    @Test
    public void test_06() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(12L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> accountService.deleteAccount(1L, "1234567890"));

        //then
        assertEquals(ErrorCode.USER_NOT_FOUND, e.getErrorCode());
    }

    @DisplayName("07. delete account id unMatch")
    @Test
    public void test_07() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(12L);

        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));

        given(accountRepository.findByAccountNumber(any()))
                .willReturn(Optional.of(Account.builder()
                        .accountUser(AccountUser.builder()
                                .build())
                        .accountStatus(AccountStatus.UNREGISTERED)
                        .accountNumber("1234567890")
                        .balance(0L)
                        .build()));

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> accountService.deleteAccount(1L, "1234567890"));

        //then
        assertEquals(ErrorCode.USER_ACCOUNT_UN_MATCH, e.getErrorCode());
    }

    @DisplayName("08. delete account already delete")
    @Test
    public void test_08() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(12L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));

        given(accountRepository.findByAccountNumber(any()))
                .willReturn(Optional.of(Account.builder()
                        .accountUser(accountUser)
                        .accountStatus(AccountStatus.UNREGISTERED)
                        .accountNumber("1234567890")
                        .balance(0L)
                        .build()));

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> accountService.deleteAccount(1L, "1234567890"));

        //then
        assertEquals(ErrorCode.ACCOUNT_ALREADY_UNREGISTERED, e.getErrorCode());
    }

    @DisplayName("09. delete account balance is not empty")
    @Test
    public void test_09() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));

        given(accountRepository.findByAccountNumber(any()))
                .willReturn(Optional.of(Account.builder()
                        .accountUser(accountUser)
                        .accountStatus(AccountStatus.IN_USE)
                        .accountNumber("1234567890")
                        .balance(1000L)
                        .build()));

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> accountService.deleteAccount(1L, "1234567890"));

        //then
        assertEquals(ErrorCode.BALANCE_NOT_EMPTY, e.getErrorCode());
    }

    @DisplayName("10. get account by user id success")
    @Test
    public void test_10() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(12L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));

        List<Account> list = new ArrayList<>();
        list.add(Account.builder()
                .accountUser(accountUser)
                .accountNumber("09876554321")
                .balance(1L)
                .build());
        list.add(Account.builder()
                .accountUser(accountUser)
                .accountNumber("1234567890")
                .balance(1234L)
                .build());
        list.add(Account.builder()
                .accountUser(accountUser)
                .accountNumber("1234512345")
                .balance(4321L)
                .build());
        given(accountRepository.findByAccountUser(any()))
                .willReturn(list);

        //when
        List<AccountDto> accounts =
                accountService.getAccountsByUserId(accountUser.getId());

        //then
        assertEquals(3, accounts.size());
    }

    @DisplayName("11. get account by user id not found user")
    @Test
    public void test_11() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .name("Pobi").build();
        accountUser.setId(12L);
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> accountService.getAccountsByUserId(accountUser.getId()));

        //then
        assertEquals(ErrorCode.USER_NOT_FOUND, e.getErrorCode());
    }
}