package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.Dining;

public interface DiningRepository extends JpaRepository<Dining, Long> {
}
