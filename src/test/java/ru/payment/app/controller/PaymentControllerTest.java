package ru.payment.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.payment.app.Application;
import ru.payment.app.model.dto.NewPaymentWithCommisionDTO;
import ru.payment.app.model.dto.PaymentDTO;
import ru.payment.app.service.PaymentService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes={Application.class})
public class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private  PaymentService paymentService;
    @Test
    void save_thenReturnBadRequest() throws Exception {
        final String url = "/payment";
        PaymentDTO paymentDTO = PaymentDTO.builder()
                .phoneNumber("+79143332345")
                .amountOfPayment(new BigDecimal("-1"))
                .comment("")
                .paymentDate(LocalDateTime.now())
                .build();

        when(paymentService.calculate(paymentDTO)).thenReturn(new NewPaymentWithCommisionDTO());
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(paymentDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(400,mvcResult.getResponse().getStatus());
    }

    @Test
    void save_thenReturnOk() throws Exception {
        final String url = "/payment";
        PaymentDTO paymentDTO = PaymentDTO.builder()
                .phoneNumber("+79143332345")
                .amountOfPayment(new BigDecimal("0"))
                .comment("")
                .paymentDate(LocalDateTime.now())
                .build();

        when(paymentService.calculate(paymentDTO)).thenReturn(new NewPaymentWithCommisionDTO());
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(paymentDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(200,mvcResult.getResponse().getStatus());
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }
}
