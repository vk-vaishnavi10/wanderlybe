package wanderly.wanderly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.TransportBooking;
import wanderly.wanderly.repository.TransportRepository;
import wanderly.wanderly.repository.UserRepository;
import wanderly.wanderly.service.TransportBookingService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transport-bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class TransportBookingController {

    @Autowired
    private TransportBookingService transportBookingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransportRepository transportRepository;

    // ✅ 1. Create a booking
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody TransportBooking booking) {
        try {
            if (booking.getUser() == null || booking.getTransport() == null) {
                return ResponseEntity.badRequest().body("User or Transport details missing!");
            }

            // ⚙️ Disable strict validation for local/demo use
            // If the user or transport doesn't exist in DB, just skip validation for now.
            // Later, once login and transport catalog are ready, re-enable this block.
            /*
            if (!userRepository.existsById(booking.getUser().getId())) {
                return ResponseEntity.badRequest().body("Invalid user ID!");
            }
            if (!transportRepository.existsById(booking.getTransport().getId())) {
                return ResponseEntity.badRequest().body("Invalid transport ID!");
            }
            */

            // ✅ Save booking
            TransportBooking saved = transportBookingService.saveBooking(booking);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body("Error saving booking: " + e.getMessage());
        }
    }

    // ✅ 2. Get all bookings
    @GetMapping
    public List<TransportBooking> getAllBookings() {
        return transportBookingService.getAllBookings();
    }

    // ✅ 3. Get booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        Optional<TransportBooking> booking = transportBookingService.getBookingById(id);
        return booking.isPresent()
                ? ResponseEntity.ok(booking.get())
                : ResponseEntity.notFound().build();
    }

    // ✅ 4. Get all bookings by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(transportBookingService.getBookingsByUser(userId));
    }

    // ✅ 5. Delete booking
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        transportBookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully!");
    }
}
