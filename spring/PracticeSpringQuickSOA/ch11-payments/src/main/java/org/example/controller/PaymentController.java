package main.java.org.example.controller;

import java.util.UUID;
import java.util.logging.Logger;
import main.java.org.example.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    Logger paymentLogger;

    @Autowired
    PaymentController(Logger paymentLogger) {
        this.paymentLogger = paymentLogger;
    }

    @PostMapping("/payment")
    public ResponseEntity<Payment> payment(
            @RequestHeader("id") String id,
            @RequestBody Payment payment
    ) {
        paymentLogger.info("Payment " + id + " for amount " + payment.getAmount() + " received");
        payment.setId(UUID.randomUUID().toString());
        paymentLogger.info("ID generated");
        paymentLogger.info("Payment send");
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("Status", Series.SUCCESSFUL.name())
                .body(payment);
    }
}
