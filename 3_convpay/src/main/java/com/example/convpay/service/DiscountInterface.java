package com.example.convpay.service;

import com.example.convpay.dto.PayRequest;

public interface DiscountInterface {

    // 할인 된 금액 받기
    Integer getDiscountedAmount(PayRequest payRequest);
}
