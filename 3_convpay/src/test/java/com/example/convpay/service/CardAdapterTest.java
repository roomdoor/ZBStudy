package com.example.convpay.service;

import com.example.convpay.type.CardUseCancelResult;
import com.example.convpay.type.CardUseResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardAdapterTest {
    CardAdapter cardAdapter = new CardAdapter();

    @DisplayName("1. capture success")
    @Test
    public void test_1(){
        //given
        Integer payAmount = 100;
        //when
        CardUseResult cardUseResult = cardAdapter.capture(payAmount);
        //then
        assertEquals(CardUseResult.USE_SUCCESS, cardUseResult);
    }

    @DisplayName("2. capture fail")
    @Test
    public void test_2(){
        //given
        Integer payAmount = 101;
        //when
        CardUseResult cardUseResult = cardAdapter.capture(payAmount);
        //then
        assertEquals(CardUseResult.USE_FAIL, cardUseResult);
    }

    @DisplayName("3. cancelCapture success")
    @Test
    public void test_3(){
        //given
        Integer payCancelAmount = 1000;
        //when
        CardUseCancelResult cardUseCancelResult = cardAdapter.cancelCapture(payCancelAmount);
        //then
        assertEquals(CardUseCancelResult.USE_CANCEL_SUCCESS, cardUseCancelResult);
    }

    @DisplayName("4. cancelCapture fail")
    @Test
    public void test_4(){
        //given
        Integer payCancelAmount = 999;
        //when
        CardUseCancelResult cardUseCancelResult = cardAdapter.cancelCapture(payCancelAmount);
        //then
        assertEquals(CardUseCancelResult.USE_CANCEL_FAIL, cardUseCancelResult);
    }


}