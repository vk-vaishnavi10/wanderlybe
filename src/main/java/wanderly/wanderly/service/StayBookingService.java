package wanderly.wanderly.service;

import org.springframework.stereotype.Service;
import wanderly.wanderly.entity.Stay;
import wanderly.wanderly.entity.User;
import wanderly.wanderly.entity.StayBooking;
import wanderly.wanderly.repository.StayBookingRepository;
import wanderly.wanderly.repository.StayRepository;
import wanderly.wanderly.repository.UserRepository;
import java.util.List;

@Service
public class StayBookingService {

    private final StayBookingRepository bookingRepo;
    private final StayRepository stayRepo;
    private final UserRepository userRepo;

    public StayBookingService(StayBookingRepository bookingRepo, StayRepository stayRepo, UserRepository userRepo) {
        this.bookingRepo = bookingRepo;
        this.stayRepo = stayRepo;
        this.userRepo = userRepo;
    }

    public StayBooking saveBooking(StayBooking booking) {
        if (booking.getStay() == null || booking.getUser() == null) {
            throw new IllegalArgumentException("Stay and User cannot be null");
        }

        // ✅ Fetch attached entities from DB (so Hibernate can manage them)
        Stay stay = stayRepo.findById(booking.getStay().getId())
                .orElseThrow(() -> new RuntimeException("Stay not found"));
        User user = userRepo.findById(booking.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        booking.setStay(stay);
        booking.setUser(user);

        return bookingRepo.save(booking);
    }

    public List<StayBooking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public void deleteBooking(Long id) {
        bookingRepo.deleteById(id);
    }
}
