package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
