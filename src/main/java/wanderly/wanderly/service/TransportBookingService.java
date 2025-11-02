package wanderly.wanderly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wanderly.wanderly.entity.TransportBooking;
import wanderly.wanderly.repository.TransportBookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransportBookingService {

    @Autowired
    private TransportBookingRepository transportBookingRepository;

    // ✅ Save booking
    public TransportBooking saveBooking(TransportBooking booking) {
        return transportBookingRepository.save(booking);
    }

    // ✅ Get all bookings
    public List<TransportBooking> getAllBookings() {
        return transportBookingRepository.findAll();
    }

    // ✅ Get booking by ID
    public Optional<TransportBooking> getBookingById(Long id) {
        return transportBookingRepository.findById(id);
    }

    // ✅ Get bookings by user
    public List<TransportBooking> getBookingsByUser(Long userId) {
        return transportBookingRepository.findByUserId(userId);
    }

    // ✅ Delete booking
    public void deleteBooking(Long id) {
        transportBookingRepository.deleteById(id);
    }
}


