package wanderly.wanderly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.Dining;
import wanderly.wanderly.entity.DiningReservation;
import wanderly.wanderly.entity.User;
import wanderly.wanderly.repository.DiningRepository;
import wanderly.wanderly.repository.DiningReservationRepository;
import wanderly.wanderly.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dining-reservations")
@CrossOrigin(origins = "http://localhost:5173")
public class DiningReservationController {

    private final DiningReservationRepository reservationRepository;
    private final DiningRepository diningRepository;
    private final UserRepository userRepository;

    public DiningReservationController(
            DiningReservationRepository reservationRepository,
            DiningRepository diningRepository,
            UserRepository userRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.diningRepository = diningRepository;
        this.userRepository = userRepository;
    }

    // ✅ Get all dining reservations
    @GetMapping
    public ResponseEntity<List<DiningReservation>> getAllReservations() {
        return ResponseEntity.ok(reservationRepository.findAll());
    }

    // ✅ Add new reservation safely
    @PostMapping
    public ResponseEntity<?> addReservation(@RequestBody DiningReservation request) {
        try {
            if (request.getUser() == null || request.getDining() == null) {
                return ResponseEntity.badRequest().body("❌ Missing user or dining details");
            }

            Optional<User> userOpt = userRepository.findById(request.getUser().getId());
            Optional<Dining> diningOpt = diningRepository.findById(request.getDining().getId());

            if (userOpt.isEmpty() || diningOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("❌ Invalid user or dining ID");
            }

            DiningReservation newReservation = new DiningReservation();
            newReservation.setUser(userOpt.get());
            newReservation.setDining(diningOpt.get());
            newReservation.setReservationTime(request.getReservationTime());
            newReservation.setPax(request.getPax());

            reservationRepository.save(newReservation);
            return ResponseEntity.ok("✅ Dining reservation saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("❌ Error saving reservation: " + e.getMessage());
        }
    }
}
