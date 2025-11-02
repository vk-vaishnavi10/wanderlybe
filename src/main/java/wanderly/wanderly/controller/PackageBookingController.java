package wanderly.wanderly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.PackageBooking;
import wanderly.wanderly.repository.PackageBookingRepository;

import java.util.List;

@RestController
@RequestMapping("/api/package-bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class PackageBookingController {

    @Autowired
    private PackageBookingRepository bookingRepo;

    @PostMapping
    public PackageBooking createBooking(@RequestBody PackageBooking booking) {
        return bookingRepo.save(booking);
    }

    @GetMapping
    public List<PackageBooking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @GetMapping("/{id}")
    public PackageBooking getBookingById(@PathVariable Long id) {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingRepo.deleteById(id);
    }
}

