package ru.payment.app.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PaymentDTO {

    BigDecimal amountOfPayment;
    LocalDateTime paymentDate;
    String comment;
    String phoneNumber;
}
