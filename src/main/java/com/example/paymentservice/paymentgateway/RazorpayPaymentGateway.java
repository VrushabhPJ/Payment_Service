package com.example.paymentservice.paymentgateway;

import com.example.paymentservice.config.PaymentGatewayConfig;
import com.razorpay.*;
import org.json.JSONObject;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

@Service
public class RazorpayPaymentGateway implements PaymentGateway{
    private RazorpayClient razorpayClient;

    public RazorpayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient= razorpayClient;
    }

    @Override
    public String generatePaymentLink(String OrderId, Long amount, String phoneNumber, String email) throws RazorpayException {
        //RazorpayClient razorpay = new RazorpayClient("[YOUR_KEY_ID]", "[YOUR_KEY_SECRET]");
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
        //paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1707325003);
        paymentLinkRequest.put("reference_id", OrderId);
        paymentLinkRequest.put("description","Payment for policy on 7 feb");
        JSONObject customer = new JSONObject();
        customer.put("name", phoneNumber);
        customer.put("contact","Vrushabh ");
        customer.put("email",email);
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

        return payment.toString();
    }
}
