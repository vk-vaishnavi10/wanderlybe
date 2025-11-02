package wanderly.wanderly.service;

import org.springframework.stereotype.Service;
import wanderly.wanderly.entity.StayBooking;
import wanderly.wanderly.repository.StayBookingRepository;
import java.util.List;

@Service
public class StayBookingService {

    private final StayBookingRepository repo;

    public StayBookingService(StayBookingRepository repo) {
        this.repo = repo;
    }

    public StayBooking saveBooking(StayBooking booking) {
        if (booking.getStay() == null) {
            throw new IllegalArgumentException("Stay cannot be null");
        }
        if (booking.getUser() == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return repo.save(booking);
    }

    public List<StayBooking> getAllBookings() {
        return repo.findAll();
    }

    public void deleteBooking(Long id) {
        repo.deleteById(id);
    }
}

