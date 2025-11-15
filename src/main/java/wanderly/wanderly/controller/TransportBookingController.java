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
            if (booking.getUser() == null || booking.getUser().getId() == null) {
                return ResponseEntity.badRequest().body("❌ Missing user ID in booking payload!");
            }
            if (booking.getTransport() == null || booking.getTransport().getId() == null) {
                return ResponseEntity.badRequest().body("❌ Missing transport ID in booking payload!");
            }
    
            // 🧩 Resolve proper User & Transport from DB so Hibernate can manage them
            var userOpt = userRepository.findById(booking.getUser().getId());
            if (!userOpt.isPresent()) {
                return ResponseEntity.badRequest().body("⚠️ Invalid user ID!");
            }
            var transportOpt = transportRepository.findById(booking.getTransport().getId());
            if (!transportOpt.isPresent()) {
                return ResponseEntity.badRequest().body("⚠️ Invalid transport ID!");
            }
    
            booking.setUser(userOpt.get());
            booking.setTransport(transportOpt.get());
    
            // 🗓️ Basic validation
            if (booking.getBookingDate() == null) {
                return ResponseEntity.badRequest().body("⚠️ bookingDate is required (use ISO format: yyyy-MM-dd'T'HH:mm:ss)");
            }
    
            // ✅ Save booking
            TransportBooking saved = transportBookingService.saveBooking(booking);
            return ResponseEntity.ok(saved);
    
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("💥 Error saving booking: " + e.getMessage());
        }
    }
}    