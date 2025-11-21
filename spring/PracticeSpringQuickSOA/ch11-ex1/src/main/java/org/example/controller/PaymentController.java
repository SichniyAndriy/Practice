package org.example.controller;

import java.util.UUID;
import org.example.model.Payment;
import org.example.proxy.PaymentProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final PaymentProxy paymentProxy;

    @Autowired
    PaymentController(PaymentProxy paymentProxy) {
        this.paymentProxy = paymentProxy;
    }

    @PostMapping("/make")
    public Payment doPayment(
            @RequestBody Payment payment
    ) {
        String id = UUID.randomUUID().toString();
        return paymentProxy.createPayment(id, payment);
    }

}
