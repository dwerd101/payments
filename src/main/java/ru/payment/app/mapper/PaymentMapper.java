package ru.payment.app.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.payment.app.model.Payment;
import ru.payment.app.model.dto.PaymentDTO;

import java.time.LocalDateTime;

@org.mapstruct.Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface PaymentMapper extends Mapper<Payment, PaymentDTO> {
    @Mappings({
            @Mapping(target="amountOfPayment", source="payment.amountOfPayment"),
            @Mapping(target="paymentDate", source="payment.paymentDate"),
            @Mapping(target="comment", source="payment.comment"),
            @Mapping(target="phoneNumber", source="payment.userId.phoneNumber"),
    })
    PaymentDTO toDTO(Payment payment);

    @Mappings({
            @Mapping(target="amountOfPayment", source="paymentDTO.amountOfPayment"),
            @Mapping(target="paymentDate", source="paymentDTO.paymentDate"),
            @Mapping(target="comment", source="paymentDTO.comment"),
            @Mapping(target="userId.phoneNumber", source="phoneNumber"),
    })
    Payment toModel(PaymentDTO paymentDTO);
}
