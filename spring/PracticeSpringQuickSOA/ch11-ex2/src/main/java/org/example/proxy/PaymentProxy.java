package org.example.proxy;

import org.example.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentProxy {
    @Value("${url}")
    private String url;

    private final RestTemplate restTemplate;

    @Autowired
    private PaymentProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Payment> createPayment(
            @RequestHeader("id") String id,
            @RequestBody Payment payment
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("id", id);
        HttpEntity<Payment> httpEntity = new HttpEntity<>(payment, headers);
        String uri = url + "/payment";

        return restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Payment.class);
    }
}
