package com.example.convpay.service;

import com.example.convpay.dto.PayRequest;
import com.example.convpay.type.ConvenienceType;
import com.example.convpay.type.PayMethodType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountByConvenienceTest {
    DiscountByConvenience discountByConvenience = new DiscountByConvenience();

    @DisplayName("1. discount test")
    @Test
    public void test_1() {
        // GU
        //given
        PayRequest payRequestGU = new PayRequest(PayMethodType.MONEY, ConvenienceType.GU, 1000);
        PayRequest payRequestG25 = new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 1000);
        PayRequest payRequestSEVNE = new PayRequest(PayMethodType.MONEY, ConvenienceType.SEVEN, 1000);
        // when
        Integer discountedAmountGU = discountByConvenience.getDiscountedAmount(payRequestGU);
        Integer discountedAmountG25 = discountByConvenience.getDiscountedAmount(payRequestG25);
        Integer discountedAmountSEVEN = discountByConvenience.getDiscountedAmount(payRequestSEVNE);
        //then
        assertEquals(900, discountedAmountGU);
        assertEquals(800, discountedAmountG25);
        assertEquals(1000, discountedAmountSEVEN);


    }

}