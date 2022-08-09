package com.example.convpay.service;

import com.example.convpay.dto.PayRequest;
import com.example.convpay.type.ConvenienceType;
import com.example.convpay.type.PayMethodType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountByPayMethodeTest {
    DiscountByPayMethode discountByPayMethode = new DiscountByPayMethode();

    @DisplayName("1. discount test")
    @Test
    public void test_1() {
        //given
        PayRequest payRequestMoney = new PayRequest(PayMethodType.MONEY
                , ConvenienceType.GU, 1000);
        PayRequest payRequestCard = new PayRequest(PayMethodType.CARD
                , ConvenienceType.GU, 1000);

        //when
        Integer discountedAmountByMoney = discountByPayMethode
                .getDiscountedAmount(payRequestMoney);
        Integer discountedAmountByCard = discountByPayMethode
                .getDiscountedAmount(payRequestCard);

        //then
        assertEquals(700, discountedAmountByMoney);
        assertEquals(1000, discountedAmountByCard);
    }

}