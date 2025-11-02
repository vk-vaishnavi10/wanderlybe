package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
