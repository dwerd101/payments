package ru.payment.app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.payment.app.Application;
import ru.payment.app.mapper.PaymentMapper;
import ru.payment.app.model.Payment;
import ru.payment.app.model.User;
import ru.payment.app.model.dto.NewPaymentWithCommisionDTO;
import ru.payment.app.model.dto.PaymentDTO;
import ru.payment.app.repository.PaymentRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
public class PaymentServiceTests {
    @Autowired
    private PaymentService paymentService;



    @Test
    void ifPriceEqualsZero_returnPriceZeroOk() {
        User user = new User();
        Payment payment = new Payment();
        payment.setAmountOfPayment(new BigDecimal("0"));
        payment.setPaymentDate(LocalDateTime.now());
        payment.setComment("nope");
        user.setPhoneNumber("+79144435671");
        payment.setUserId(user);
      NewPaymentWithCommisionDTO paymentWithCommisionDTO = paymentService.calculate(PaymentDTO.builder()
                .phoneNumber(payment.getUserId().getPhoneNumber())
                .amountOfPayment(payment.getAmountOfPayment())
                .comment("")
                .paymentDate(LocalDateTime.now())
                .build());
      assertEquals(new BigDecimal("0"), paymentWithCommisionDTO.getSummOfPayment());
    }
    @Test
    void ifPriceEqualsZero_returnPriceOneOk1() {
        User user = new User();
        Payment payment = new Payment();
        payment.setAmountOfPayment(new BigDecimal("0"));
        payment.setPaymentDate(LocalDateTime.now());
        payment.setComment("nope");
        user.setPhoneNumber("+79144435671");
        payment.setUserId(user);
        NewPaymentWithCommisionDTO paymentWithCommisionDTO = paymentService.calculate(PaymentDTO.builder()
                .phoneNumber(payment.getUserId().getPhoneNumber())
                .amountOfPayment(payment.getAmountOfPayment())
                .comment("")
                .paymentDate(LocalDateTime.now())
                .build());
        assertEquals(new BigDecimal("0"), paymentWithCommisionDTO.getSummOfPayment());
    }
    @Test
    void ifPriceEquals150_returnPrice152Ok() {
        User user = new User();
        Payment payment = new Payment();
        payment.setAmountOfPayment(new BigDecimal("150"));
        payment.setPaymentDate(LocalDateTime.now());
        payment.setComment("nope");
        user.setPhoneNumber("+79144435671");
        payment.setUserId(user);
        NewPaymentWithCommisionDTO paymentWithCommisionDTO = paymentService.calculate(PaymentDTO.builder()
                .phoneNumber(payment.getUserId().getPhoneNumber())
                .amountOfPayment(payment.getAmountOfPayment())
                .comment("")
                .paymentDate(LocalDateTime.now())
                .build());
        assertEquals(new BigDecimal("152"), paymentWithCommisionDTO.getSummOfPayment());
    }
    @Test
    void ifPriceEquals10000_returnPrice10310Ok() {
        User user = new User();
        Payment payment = new Payment();
        payment.setAmountOfPayment(new BigDecimal("10000"));
        payment.setPaymentDate(LocalDateTime.now());
        payment.setComment("nope");
        user.setPhoneNumber("+79144435671");
        payment.setUserId(user);
        NewPaymentWithCommisionDTO paymentWithCommisionDTO = paymentService.calculate(PaymentDTO.builder()
                .phoneNumber(payment.getUserId().getPhoneNumber())
                .amountOfPayment(payment.getAmountOfPayment())
                .comment("")
                .paymentDate(LocalDateTime.now())
                .build());
        assertEquals(new BigDecimal("10310"), paymentWithCommisionDTO.getSummOfPayment());
    }
    @Test
    void ifPriceEquals10000_returnPrice10310O1k() {
        User user = new User();
        Payment payment = new Payment();
        payment.setAmountOfPayment(new BigDecimal("100000"));
        payment.setPaymentDate(LocalDateTime.now());
        payment.setComment("nope");
        user.setPhoneNumber("+79144435671");
        payment.setUserId(user);
        NewPaymentWithCommisionDTO paymentWithCommisionDTO = paymentService.calculate(PaymentDTO.builder()
                .phoneNumber(payment.getUserId().getPhoneNumber())
                .amountOfPayment(payment.getAmountOfPayment())
                .comment("")
                .paymentDate(LocalDateTime.now())
                .build());
        assertEquals(new BigDecimal("105264"), paymentWithCommisionDTO.getSummOfPayment());
    }
}
