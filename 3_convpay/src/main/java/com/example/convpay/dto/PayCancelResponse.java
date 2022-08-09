package com.example.convpay.dto;

import com.example.convpay.type.PayCancelResult;

public class PayCancelResponse {
    // 취소 결과
    PayCancelResult payCancelResult;

    // 결제 취소 금액
    Integer payCancelAmount;

    public PayCancelResponse(PayCancelResult payCancelResult, Integer payCancelAmount) {
        this.payCancelResult = payCancelResult;
        this.payCancelAmount = payCancelAmount;
    }

    public PayCancelResult getPayCancelResult() {
        return payCancelResult;
    }

    public void setPayCancelResult(PayCancelResult payCancelResult) {
        this.payCancelResult = payCancelResult;
    }

    public Integer getPayCancelAmount() {
        return payCancelAmount;
    }

    public void setPayCancelAmount(Integer payCancelAmount) {
        this.payCancelAmount = payCancelAmount;
    }

    @Override
    public String toString() {
        return "PayCancelResponse{" +
                "payCancelResult=" + payCancelResult +
                ", payCancelAmount=" + payCancelAmount +
                '}';
    }
}
