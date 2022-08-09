package com.example.convpay;

import com.example.convpay.config.ApplicationConfig;
import com.example.convpay.dto.PayCancelRequest;
import com.example.convpay.dto.PayCancelResponse;
import com.example.convpay.dto.PayRequest;
import com.example.convpay.dto.PayResponse;
import com.example.convpay.service.ConveniencePayService;
import com.example.convpay.type.ConvenienceType;
import com.example.convpay.type.PayMethodType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 사용자
public class UserClient {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);

        ConveniencePayService conveniencePayService =
                applicationContext.getBean(
                        "conveniencePayService", ConveniencePayService.class);

        PayResponse payResponse = conveniencePayService.pay(
                new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 1000));
        System.out.println(payResponse.toString());

        System.out.println("==================================================");
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(
                new PayCancelRequest(PayMethodType.CARD, ConvenienceType.G25, 3000));
        System.out.println(payCancelResponse.toString());

    }
}
