package com.example.account.service;

import com.example.account.exceoption.AccountException;
import com.example.account.type.ErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import static com.example.account.type.ErrorCode.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class LockServiceTest {
    @Mock
    private RedissonClient redissonClient;

    @Mock
    private RLock rLock;

    @InjectMocks
    private LockService lockService;

    @DisplayName("01. success get lock")
    @Test
    public void test_01() {
        //given
        given(redissonClient.getLock(anyString()))
                .willReturn(rLock);
        given(rLock.tryLock())
                .willReturn(true);
        //when
        assertDoesNotThrow(() -> lockService.lock("1234"));
        //then

    }

    @DisplayName("02. fail get lock")
    @Test
    public void test_02() throws InterruptedException {
        //given
        given(redissonClient.getLock(anyString()))
                .willReturn(rLock);
        given(rLock.tryLock(anyLong(), anyLong(), any()))
                .willReturn(false);
        //when

        AccountException exception = assertThrows(AccountException.class,
                () -> lockService.lock("1234"));
        //then
        assertEquals(ACCOUNT_TRANSACTION_LOCK, exception.getErrorCode());
    }


}