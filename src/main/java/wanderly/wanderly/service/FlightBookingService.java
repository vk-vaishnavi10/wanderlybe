package wanderly.wanderly.service;

import org.springframework.stereotype.Service;
import wanderly.wanderly.entity.FlightBooking;
import wanderly.wanderly.repository.FlightBookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FlightBookingService {
    private final FlightBookingRepository repository;

    public FlightBookingService(FlightBookingRepository repository) {
        this.repository = repository;
    }

    public List<FlightBooking> getAllBookings() {
        return repository.findAll();
    }

    public Optional<FlightBooking> getBookingById(Long id) {
        return repository.findById(id);
    }

    public FlightBooking saveBooking(FlightBooking booking) {
        return repository.save(booking);
    }

    public void deleteBooking(Long id) {
        repository.deleteById(id);
    }
}
