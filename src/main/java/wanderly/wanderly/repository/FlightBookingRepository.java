package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.FlightBooking;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {
}
