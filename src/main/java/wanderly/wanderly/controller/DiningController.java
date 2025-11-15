package wanderly.wanderly.controller;

import jakarta.annotation.PostConstruct;
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

    // ✅ AUTO-SEED dining data when backend starts
    @PostConstruct
    public void autoSeedDining() {
        if (diningRepository.count() == 0) {
            System.out.println("🍴 Dining table empty — seeding sample data...");
            seedDiningData();  // call the same method below
        } else {
            System.out.println("✅ Dining data already present, skipping seed.");
        }
    }

    // ✅ Fetch all dining places
    @GetMapping
    public ResponseEntity<List<Dining>> getAllDining() {
        return ResponseEntity.ok(diningRepository.findAll());
    }

    // ✅ Add new dining record manually (if needed)
    @PostMapping
    public ResponseEntity<Dining> createDining(@RequestBody Dining dining) {
        return ResponseEntity.ok(diningRepository.save(dining));
    }

    // ✅ Manual seed endpoint (optional)
    @PostMapping("/seed")
    public ResponseEntity<String> seedDiningData() {
        if (diningRepository.count() > 0) {
            return ResponseEntity.ok("🍽️ Dining data already exists!");
        }

        Dining r1 = new Dining();
        r1.setName("The Royal Feast");
        r1.setCuisine("Multi-Cuisine Luxury");
        r1.setLocation("Taj Palace, Mumbai");
        r1.setDescription("Enjoy fine dining with a royal ambience and exquisite international cuisines.");
        r1.setImageUrl("https://images.unsplash.com/photo-1550966871-3ed3cdb5ed0c?w=1000");

        Dining r2 = new Dining();
        r2.setName("Bean & Brew Café");
        r2.setCuisine("Local Coffee & Pastries");
        r2.setLocation("Brigade Road, Bangalore");
        r2.setDescription("A cozy café serving artisanal coffees and freshly baked treats.");
        r2.setImageUrl("https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=1000");

        Dining r3 = new Dining();
        r3.setName("Spice Street");
        r3.setCuisine("Authentic Indian Street Food");
        r3.setLocation("Chandni Chowk, Delhi");
        r3.setDescription("Indulge in flavorful chaats, kebabs, and sweets in a lively atmosphere.");
        r3.setImageUrl("https://images.unsplash.com/photo-1551782450-a2132b4ba21d?w=1000");

        diningRepository.saveAll(List.of(r1, r2, r3));

        return ResponseEntity.ok("✅ Dining data seeded successfully!");
    }
}
