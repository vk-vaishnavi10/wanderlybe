package wanderly.wanderly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderly.wanderly.entity.TripPackage;

public interface PackageRepository extends JpaRepository<TripPackage, Long> {
}
