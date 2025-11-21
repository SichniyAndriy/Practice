package org.example.proxy;

import org.example.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "payments", url = "${url}")
public interface PaymentProxy {
    @PostMapping("/payment")
    Payment createPayment(
            @RequestHeader String id,
            @RequestBody Payment payment);
}
