package com.example.convpay.service;

import com.example.convpay.type.MoneyUseCancelResult;
import com.example.convpay.type.MoneyUseResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyAdapterTest {
    MoneyAdapter moneyAdapter = new MoneyAdapter();

    @DisplayName("1. moneyAdepter.use FAIL")
    @Test
    public void test_1(){
        //given
        Integer payAmount = 1000001;
        //when
        MoneyUseResult moneyUseResult = moneyAdapter.use(payAmount);

        //then
        assertEquals(MoneyUseResult.USE_FAIL, moneyUseResult);
    }

    @DisplayName("2. moneyAdepter.use SUCCESS")
    @Test
    public void test_2(){
        //given
        Integer payAmount = 1000000;
        //when
        MoneyUseResult moneyUseResult = moneyAdapter.use(payAmount);

        //then
        assertEquals(MoneyUseResult.USE_SUCCESS, moneyUseResult);
    }

    @DisplayName("3. moneyAdepter.useCancel SUCCESS")
    @Test
    public void test_3(){
        //given
        Integer payCancelAmount = 100;
        //when
        MoneyUseCancelResult moneyUseCancelResult = moneyAdapter.useCancel(payCancelAmount);
        //then
        assertEquals(MoneyUseCancelResult.MONEY_USE_CANCEL_SUCCESS, moneyUseCancelResult);
    }

    @DisplayName("4. moneyAdepter.useCancel FAIL")
    @Test
    public void test_4(){
        //given
        Integer payCancelAmount = 99;
        //when
        MoneyUseCancelResult moneyUseCancelResult = moneyAdapter.useCancel(payCancelAmount);
        //then
        assertEquals(MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL, moneyUseCancelResult);
    }

}