package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.EventBooking;

public interface EventBookingRepository extends JpaRepository<EventBooking, Long> {
}
