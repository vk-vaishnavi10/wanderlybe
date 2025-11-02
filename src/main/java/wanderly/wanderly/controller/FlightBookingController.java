package wanderly.wanderly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.FlightBooking;
import wanderly.wanderly.service.FlightBookingService;

import java.util.List;

@RestController
@RequestMapping("/api/flight-bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class FlightBookingController {

    @Autowired
    private FlightBookingService bookingService;

    @PostMapping
    public FlightBooking createBooking(@RequestBody FlightBooking booking) {
        System.out.println("🎫 Received booking: " + booking);
        return bookingService.saveBooking(booking);
    }

    @GetMapping
    public List<FlightBooking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public FlightBooking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}

