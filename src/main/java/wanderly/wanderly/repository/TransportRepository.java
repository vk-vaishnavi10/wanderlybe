package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.Transport;

public interface TransportRepository extends JpaRepository<Transport, Long> {}
