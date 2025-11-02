package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanderly.wanderly.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    // You can add custom queries here later
}
