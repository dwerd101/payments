package ru.payment.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.payment.app.exception.AmountOfPaymentLessThenOneException;
import ru.payment.app.model.Payment;
import ru.payment.app.model.dto.PaymentDTO;
import ru.payment.app.model.dto.NewPaymentWithCommisionDTO;
import ru.payment.app.repository.PaymentRepository;
import ru.payment.app.service.PaymentService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("/payment")
    public ResponseEntity<NewPaymentWithCommisionDTO> calulateCommision(@RequestBody PaymentDTO paymentDTO) {
        if(paymentDTO.getAmountOfPayment().compareTo(new BigDecimal("-1")) == 0 ) {
            throw new AmountOfPaymentLessThenOneException();
        }
        return ResponseEntity.ok(paymentService.calculate(paymentDTO));
    }
}
