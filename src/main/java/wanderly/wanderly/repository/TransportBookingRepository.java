package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.TransportBooking;

import java.util.List;

public interface TransportBookingRepository extends JpaRepository<TransportBooking, Long> {
    List<TransportBooking> findByUserId(Long userId);
    List<TransportBooking> findByTransportId(Long transportId);
}
