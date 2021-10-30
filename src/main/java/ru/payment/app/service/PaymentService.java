package ru.payment.app.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.payment.app.mapper.PaymentMapper;
import ru.payment.app.model.Payment;
import ru.payment.app.model.dto.PaymentDTO;
import ru.payment.app.model.dto.NewPaymentWithCommisionDTO;
import ru.payment.app.repository.PaymentRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Transactional
    public NewPaymentWithCommisionDTO savePayment(PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.toModel(paymentDTO);
        paymentRepository.save(payment);
        return calculateNewPriceWithCommision(paymentDTO);
    }
    private NewPaymentWithCommisionDTO calculateNewPriceWithCommision(PaymentDTO paymentDTO) {
        if (paymentDTO.getAmountOfPayment().compareTo(new BigDecimal("0")) == 0 || paymentDTO.getAmountOfPayment()
                .compareTo(new BigDecimal("9999")) <= 0) {
            BigDecimal oldPrice = paymentDTO.getAmountOfPayment();
            BigDecimal newPrice = oldPrice.multiply(new BigDecimal("100")).divide(new BigDecimal("99"), RoundingMode.UP);
            return new NewPaymentWithCommisionDTO(newPrice);

        } else if (paymentDTO.getAmountOfPayment().compareTo(new BigDecimal("10000")) == 0 || paymentDTO.getAmountOfPayment()
                .compareTo(new BigDecimal("99999")) <= 0) {
            BigDecimal oldPrice = paymentDTO.getAmountOfPayment();
            BigDecimal newPrice = oldPrice.multiply(new BigDecimal("100")).divide(new BigDecimal("97"),RoundingMode.UP);
            return new NewPaymentWithCommisionDTO(newPrice);

        } else {
            BigDecimal oldPrice = paymentDTO.getAmountOfPayment();
            BigDecimal newPrice = oldPrice.multiply(new BigDecimal("100")).divide(new BigDecimal("95"), RoundingMode.UP);
            return new NewPaymentWithCommisionDTO(newPrice);
        }
    }


}
