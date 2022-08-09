package com.example.convpay.service;

import com.example.convpay.dto.PayCancelRequest;
import com.example.convpay.dto.PayCancelResponse;
import com.example.convpay.dto.PayRequest;
import com.example.convpay.dto.PayResponse;
import com.example.convpay.type.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConveniencePayService {    // 편결이
    private final Map<PayMethodType, PaymentInterface> paymentInterfaceMap
            = new HashMap<>();
    private final DiscountInterface discountInterface;

    public ConveniencePayService(Set<PaymentInterface> paymentInterfaceSet,
                                 DiscountInterface discountInterface) {
        paymentInterfaceSet.forEach(paymentInterface ->
                paymentInterfaceMap.put(paymentInterface.getPaymentType(),
                        paymentInterface));
        this.discountInterface = discountInterface;
    }

    // 결제
    public PayResponse pay(PayRequest payRequest) {
        PaymentInterface paymentInterface =
                paymentInterfaceMap.get(payRequest.getPayMethodType());

        Integer discountedAmount = discountInterface.getDiscountedAmount(payRequest);
        PaymentResult paymentResult = paymentInterface.paymentResult(discountedAmount);

        // fail case 1
        if (paymentResult == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }
        // fail case 2
        // fail case 3
        // fail case 4

        // success case
        return new PayResponse(PayResult.SUCCESS, discountedAmount);
    }


    // 결제 취소
    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {
        PaymentInterface paymentInterface =
                paymentInterfaceMap.get(payCancelRequest.getPayMethodType());

        CancelPaymentResult cancelPaymentResult = paymentInterface.paymentCancelResult(payCancelRequest.getPayCancelAmount());

        if (cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL) {
            return new PayCancelResponse(PayCancelResult.CANCEL_FAIL, 0);
        }

        return new PayCancelResponse(PayCancelResult.CANCEL_SUCCESS, payCancelRequest.getPayCancelAmount());
    }
}
