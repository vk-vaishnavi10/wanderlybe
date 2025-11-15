package wanderly.wanderly.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.Transport;
import wanderly.wanderly.repository.TransportRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transport")
@CrossOrigin(origins = "http://localhost:5173") // ✅ for your Vite frontend
public class TransportController {

    private final TransportRepository transportRepository;

    public TransportController(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    // ✅ AUTO-SEED on startup
    @PostConstruct
    public void autoSeedOnStartup() {
        if (transportRepository.count() == 0) {
            System.out.println("🚗 Transport table empty — seeding default data...");
            seedTransportData();
        } else {
            System.out.println("✅ Transport data already present, skipping seeding.");
        }
    }

    // ✅ Get all transport entries
    @GetMapping
    public ResponseEntity<List<Transport>> getAllTransport() {
        return ResponseEntity.ok(transportRepository.findAll());
    }

    // ✅ Add single transport
    @PostMapping
    public ResponseEntity<Transport> createTransport(@RequestBody Transport transport) {
        return ResponseEntity.ok(transportRepository.save(transport));
    }

    // ✅ SEED endpoint (adds sample transport data)
    @PostMapping("/seed")
    public ResponseEntity<String> seedTransportData() {
        if (transportRepository.count() > 0) {
            return ResponseEntity.ok("🚗 Transport data already exists!");
        }

        Transport car1 = new Transport();
        car1.setType("Car");
        car1.setName("Sedan");
        car1.setProvider("Wanderly Rentals");
        car1.setFromCity("Hyderabad");
        car1.setToCity("Delhi");
        car1.setDepartureTime(LocalDateTime.now().plusDays(1));
        car1.setPrice(2500);

        Transport car2 = new Transport();
        car2.setType("Car");
        car2.setName("SUV");
        car2.setProvider("Wanderly Rentals");
        car2.setFromCity("Mumbai");
        car2.setToCity("Pune");
        car2.setDepartureTime(LocalDateTime.now().plusDays(2));
        car2.setPrice(4000);

        Transport car3 = new Transport();
        car3.setType("Car");
        car3.setName("Luxury");
        car3.setProvider("Wanderly Rentals");
        car3.setFromCity("Chennai");
        car3.setToCity("Bangalore");
        car3.setDepartureTime(LocalDateTime.now().plusDays(3));
        car3.setPrice(7000);

        Transport cab1 = new Transport();
        cab1.setType("Cab");
        cab1.setName("Airport Cab");
        cab1.setProvider("Wanderly Cabs");
        cab1.setFromCity("Delhi");
        cab1.setToCity("Airport");
        cab1.setDepartureTime(LocalDateTime.now().plusHours(5));
        cab1.setPrice(800);

        Transport cab2 = new Transport();
        cab2.setType("Cab");
        cab2.setName("Local Ride");
        cab2.setProvider("Wanderly Cabs");
        cab2.setFromCity("Hyderabad");
        cab2.setToCity("City Center");
        cab2.setDepartureTime(LocalDateTime.now().plusHours(2));
        cab2.setPrice(300);

        Transport cab3 = new Transport();
        cab3.setType("Cab");
        cab3.setName("Outstation Cab");
        cab3.setProvider("Wanderly Cabs");
        cab3.setFromCity("Bangalore");
        cab3.setToCity("Mysore");
        cab3.setDepartureTime(LocalDateTime.now().plusDays(1));
        cab3.setPrice(2500);

        transportRepository.saveAll(List.of(car1, car2, car3, cab1, cab2, cab3));

        return ResponseEntity.ok("✅ Transport data seeded successfully!");
    }
}
