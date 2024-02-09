package com.example.paymentservice.services;

import com.example.paymentservice.paymentgateway.PaymentGateway;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGateway paymentGateway;

    public PaymentService( PaymentGateway paymentGateway) {
        this.paymentGateway= paymentGateway;
    }

    public String initiatePayment(String OrderId, Long amount, String phoneNumber, String email) throws RazorpayException, StripeException {
//        Order order = orderService.getOrderDetails(orderId);
//        int amount = order.getAmount;
//        String productName = order.getProductName();
        //Long amount = 198767L; // 1987.67
        return paymentGateway.generatePaymentLink(OrderId, amount, phoneNumber, email);

    }
}

