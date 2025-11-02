package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.StayBooking;

public interface StayBookingRepository extends JpaRepository<StayBooking, Long> {
}
