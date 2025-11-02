package wanderly.wanderly.controller;

import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.StayBooking;
import wanderly.wanderly.service.StayBookingService;
import java.util.List;

@RestController
@RequestMapping("/api/stay-bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class StayBookingController {

    private final StayBookingService service;

    public StayBookingController(StayBookingService service) {
        this.service = service;
    }

    @PostMapping
    public StayBooking addBooking(@RequestBody StayBooking booking) {
        System.out.println("📥 Received Stay Booking: " + booking);
        return service.saveBooking(booking);
    }

    @GetMapping
    public List<StayBooking> getAllBookings() {
        return service.getAllBookings();
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        service.deleteBooking(id);
    }
}
