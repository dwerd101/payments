package ru.payment.app.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AmountOfPaymentLessThenOneException extends  RuntimeException {
    public AmountOfPaymentLessThenOneException() {
        super();
    }

    public AmountOfPaymentLessThenOneException(String message) {
        super(message);
    }

    public AmountOfPaymentLessThenOneException(String message, Throwable cause) {
        super(message, cause);
    }

    public AmountOfPaymentLessThenOneException(Throwable cause) {
        super(cause);
    }

    protected AmountOfPaymentLessThenOneException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
