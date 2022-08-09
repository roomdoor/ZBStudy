package com.example.convpay.service;

import com.example.convpay.type.CancelPaymentResult;
import com.example.convpay.type.PayMethodType;
import com.example.convpay.type.PaymentResult;

public interface PaymentInterface {

    PayMethodType getPaymentType();

    // 결제 성공 여부
    PaymentResult paymentResult(Integer payAmount);

    // 결제 취소 성공 여뷰
    CancelPaymentResult paymentCancelResult(Integer payCancelAmount);

}
