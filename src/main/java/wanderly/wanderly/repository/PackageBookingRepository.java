package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.PackageBooking;

public interface PackageBookingRepository extends JpaRepository<PackageBooking, Long> {
}
