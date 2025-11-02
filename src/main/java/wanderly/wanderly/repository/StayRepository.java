package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.Stay;

public interface StayRepository extends JpaRepository<Stay, Long> {
}
