package wanderly.wanderly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.Stay;
import wanderly.wanderly.repository.StayRepository;

import java.util.List;

@RestController
@RequestMapping("/api/stays")
public class StayController {

    private final StayRepository stayRepository;

    public StayController(StayRepository stayRepository) {
        this.stayRepository = stayRepository;
    }

    @GetMapping
    public ResponseEntity<List<Stay>> getAllStays() {
        return ResponseEntity.ok(stayRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Stay> createStay(@RequestBody Stay stay) {
        return ResponseEntity.ok(stayRepository.save(stay));
    }
}
