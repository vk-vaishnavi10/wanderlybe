package wanderly.wanderly.controller;

import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.DiningReservation;
import wanderly.wanderly.service.DiningReservationService;
import java.util.List;

@RestController
@RequestMapping("/api/dining-reservations")
@CrossOrigin(origins = "http://localhost:5173")
public class DiningReservationController {

    private final DiningReservationService service;

    public DiningReservationController(DiningReservationService service) {
        this.service = service;
    }

    @PostMapping
    public DiningReservation addReservation(@RequestBody DiningReservation reservation) {
        System.out.println("📥 Received Reservation: " + reservation);
        return service.saveReservation(reservation);
    }

    @GetMapping
    public List<DiningReservation> getAllReservations() {
        return service.getAllReservations();
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        service.deleteReservation(id);
    }
}



