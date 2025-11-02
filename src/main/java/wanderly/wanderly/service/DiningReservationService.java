package wanderly.wanderly.service;

import org.springframework.stereotype.Service;
import wanderly.wanderly.entity.DiningReservation;
import wanderly.wanderly.repository.DiningReservationRepository;
import java.util.List;

@Service
public class DiningReservationService {

    private final DiningReservationRepository repo;

    public DiningReservationService(DiningReservationRepository repo) {
        this.repo = repo;
    }

    public DiningReservation saveReservation(DiningReservation reservation) {
        if (reservation.getDining() == null)
            throw new IllegalArgumentException("Dining cannot be null");
        if (reservation.getUser() == null)
            throw new IllegalArgumentException("User cannot be null");
        return repo.save(reservation);
    }

    public List<DiningReservation> getAllReservations() {
        return repo.findAll();
    }

    public void deleteReservation(Long id) {
        repo.deleteById(id);
    }
}
