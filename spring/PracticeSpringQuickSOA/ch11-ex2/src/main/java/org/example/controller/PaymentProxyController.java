package org.example.controller;

import org.example.model.Payment;
import org.example.proxy.PaymentProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentProxyController {

    private final PaymentProxy paymentProxy;

    @Autowired
    private PaymentProxyController(PaymentProxy paymentProxy) {
        this.paymentProxy = paymentProxy;
    }

    @PostMapping("/make")
    public Payment createPayment(
            @RequestBody Payment payment
    ) {
        return paymentProxy.createPayment("6576465", payment).getBody();
    }
}
