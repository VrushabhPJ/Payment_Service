package com.example.paymentservice.paymentgateway;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String generatePaymentLink(String OrderId, Long amount, String phoneNumber, String email) throws RazorpayException, StripeException;
}
