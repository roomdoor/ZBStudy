package com.example.convpay.service;

import com.example.convpay.dto.PayCancelRequest;
import com.example.convpay.dto.PayCancelResponse;
import com.example.convpay.dto.PayRequest;
import com.example.convpay.dto.PayResponse;
import com.example.convpay.type.ConvenienceType;
import com.example.convpay.type.PayCancelResult;
import com.example.convpay.type.PayMethodType;
import com.example.convpay.type.PayResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ConveniencePayServiceTest {
    ConveniencePayService conveniencePayService = new ConveniencePayService(
            new HashSet<>(
                    Arrays.asList(new MoneyAdapter(), new CardAdapter())
            ),
            new DiscountByPayMethode()
    );

    @DisplayName("1. pay SUCCESS")
    @Test
    public void test_1() {
        //given
        PayRequest payRequest = new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 50);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.SUCCESS, payResponse.getPayResult());
        assertEquals(35, payResponse.getPaidAmount());
    }

    @DisplayName("2. pay FAIL")
    @Test
    public void test_2() {
        //given
        PayRequest payRequest = new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 2000000);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.FAIL, payResponse.getPayResult());
        assertEquals(0, payResponse.getPaidAmount());
    }

    @DisplayName("3. payCancel SUCCESS")
    @Test
    public void test_3() {
        //given
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY, ConvenienceType.G25, 100);
        //when
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);
        //then
        assertEquals(PayCancelResult.CANCEL_SUCCESS, payCancelResponse.getPayCancelResult());
    }

    @DisplayName("4. payCancel FAIL")
    @Test
    public void test_4() {
        //given
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY, ConvenienceType.G25, 99);
        //when
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);
        //then
        assertEquals(PayCancelResult.CANCEL_FAIL, payCancelResponse.getPayCancelResult());
    }


}