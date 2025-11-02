package wanderly.wanderly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.TripPackage;
import wanderly.wanderly.repository.PackageRepository;

import java.util.List;

@RestController
@RequestMapping("/api/packages")
@CrossOrigin(origins = "http://localhost:5173")
public class PackageController {

    private final PackageRepository packageRepository;

    public PackageController(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @GetMapping
    public ResponseEntity<List<TripPackage>> getAllPackages() {
        return ResponseEntity.ok(packageRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<TripPackage> createPackage(@RequestBody TripPackage tripPackage) {
        return ResponseEntity.ok(packageRepository.save(tripPackage));
    }
}

