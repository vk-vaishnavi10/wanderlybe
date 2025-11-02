package wanderly.wanderly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.EventBooking;
import wanderly.wanderly.repository.EventBookingRepository;

import java.util.List;

@RestController
@RequestMapping("/api/event-bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class EventBookingController {

    private final EventBookingRepository eventBookingRepository;

    public EventBookingController(EventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @GetMapping
    public ResponseEntity<List<EventBooking>> getAllBookings() {
        return ResponseEntity.ok(eventBookingRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<EventBooking> createBooking(@RequestBody EventBooking booking) {
        return ResponseEntity.ok(eventBookingRepository.save(booking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        eventBookingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

