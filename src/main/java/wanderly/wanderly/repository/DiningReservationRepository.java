package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.DiningReservation;

public interface DiningReservationRepository extends JpaRepository<DiningReservation, Long> {
}
