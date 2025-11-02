package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.PackagePayment;

public interface PackagePaymentRepository extends JpaRepository<PackagePayment, Long> {
}
