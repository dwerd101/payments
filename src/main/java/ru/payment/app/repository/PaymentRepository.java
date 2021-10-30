package ru.payment.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.payment.app.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
