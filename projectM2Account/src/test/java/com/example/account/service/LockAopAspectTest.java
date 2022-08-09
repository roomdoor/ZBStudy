package com.example.account.service;

import com.example.account.dto.UseBalance;
import com.example.account.exceoption.AccountException;
import com.example.account.type.ErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.account.type.ErrorCode.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LockAopAspectTest {
    @Mock
    private LockService lockService;

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @InjectMocks
    private LockAopAspect lockAopAspect;


    @DisplayName("01. lockAndUnlock")
    @Test
    public void test_01() throws Throwable {
        //given
        ArgumentCaptor<String> lockArgumentCaptor =
                ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> unlockArgumentCaptor =
                ArgumentCaptor.forClass(String.class);
        UseBalance.Request request = new UseBalance.Request(
                123L,
                "1234123412",
                1000L);
        //when
        lockAopAspect.aroundMethod(proceedingJoinPoint, request);
        //then
        verify(lockService, times(1)).
                lock(lockArgumentCaptor.capture());
        verify(lockService, times(1)).
                unlock(unlockArgumentCaptor.capture());

        assertEquals("1234123412", lockArgumentCaptor.getValue());
        assertEquals("1234123412", unlockArgumentCaptor.getValue());
    }

    @DisplayName("02. lockAndUnlock even if throw")
    @Test
    public void test_02() throws Throwable {
        //given
        UseBalance.Request request = new UseBalance.Request(
                123L,
                "54321",
                1000L);
        given(proceedingJoinPoint.proceed())
                .willThrow(new AccountException(ACCOUNT_NOT_FOUND));

        //when
        assertThrows(AccountException.class, () ->
                lockAopAspect.aroundMethod(proceedingJoinPoint, request));
        //then
    }
}