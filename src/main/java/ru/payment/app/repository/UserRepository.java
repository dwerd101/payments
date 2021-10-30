package ru.payment.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.payment.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
