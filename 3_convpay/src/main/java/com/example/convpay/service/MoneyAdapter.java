package com.example.convpay.service;

import com.example.convpay.type.*;

public class MoneyAdapter implements PaymentInterface {

    // 머니 사용
    public MoneyUseResult use(Integer payAmount) {
        System.out.println("MoneyAdapter.use : " + payAmount);

        if (payAmount > 1000_000) {
            return MoneyUseResult.USE_FAIL;
        }

        return MoneyUseResult.USE_SUCCESS;
    }


    // 머니 사용 취소
    public MoneyUseCancelResult useCancel(Integer payCancelAmount) {
        System.out.println("MoneyAdapter.useCancel : " + payCancelAmount);

        if (payCancelAmount < 100) {
            return MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL;
        }

        return MoneyUseCancelResult.MONEY_USE_CANCEL_SUCCESS;


    }

    @Override
    public PayMethodType getPaymentType() {
        return PayMethodType.MONEY;
    }

    @Override
    public PaymentResult paymentResult(Integer payAmount) {
        MoneyUseResult moneyUseResult = use(payAmount);

        if (moneyUseResult == MoneyUseResult.USE_FAIL) {
            return PaymentResult.PAYMENT_FAIL;
        }

        return PaymentResult.PAYMENT_SUCCESS;
    }

    @Override
    public CancelPaymentResult paymentCancelResult(Integer payCancelAmount) {
        MoneyUseCancelResult moneyUseCancelResult = useCancel(payCancelAmount);

        if (moneyUseCancelResult == MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL) {
            return CancelPaymentResult.CANCEL_PAYMENT_FAIL;
        }

        return CancelPaymentResult.CANCEL_PAYMENT_SUCCESS;
    }
}
