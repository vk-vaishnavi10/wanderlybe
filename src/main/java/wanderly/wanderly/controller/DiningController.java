package wanderly.wanderly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.Dining;
import wanderly.wanderly.repository.DiningRepository;
import java.util.List;

@RestController
@RequestMapping("/api/dining")
@CrossOrigin(origins = "http://localhost:5173")
public class DiningController {

    private final DiningRepository diningRepository;

    public DiningController(DiningRepository diningRepository) {
        this.diningRepository = diningRepository;
    }

    @GetMapping
    public ResponseEntity<List<Dining>> getAllDining() {
        return ResponseEntity.ok(diningRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Dining> createDining(@RequestBody Dining dining) {
        return ResponseEntity.ok(diningRepository.save(dining));
    }
}
