package wanderly.wanderly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wanderly.wanderly.entity.Transport;
import wanderly.wanderly.repository.TransportRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransportService {

    @Autowired
    private TransportRepository transportRepository;

    // ✅ Get all available transport options
    public List<Transport> getAllTransport() {
        return transportRepository.findAll();
    }

    // ✅ Save a new transport record
    public Transport saveTransport(Transport transport) {
        return transportRepository.save(transport);
    }

    // ✅ Get a single transport by ID
    public Transport getTransportById(Long id) {
        return transportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("❌ Transport not found with ID: " + id));
    }

    // ✅ Delete a transport record by ID
    public void deleteTransport(Long id) {
        transportRepository.deleteById(id);
    }

    // ✅ Quick seed method to preload data into DB
    public String seedTransportData() {
        if (transportRepository.count() > 0) {
            return "✅ Transport data already exists!";
        }

        Transport t1 = new Transport("Car", "Sedan", "Wanderly Rentals",
                "Hyderabad", "Delhi", 2000,
                "https://images.unsplash.com/photo-1502877338535-766e1452684a?w=800",
                LocalDateTime.now().plusDays(1));

        Transport t2 = new Transport("Car", "SUV", "Wanderly Rentals",
                "Bangalore", "Goa", 3500,
                "https://images.unsplash.com/photo-1617814078236-ff3e3edfab01?w=800",
                LocalDateTime.now().plusDays(2));

        Transport t3 = new Transport("Car", "Luxury", "Wanderly Rentals",
                "Mumbai", "Jaipur", 7000,
                "https://images.unsplash.com/photo-1549921296-3a13d9b2cdc8?w=800",
                LocalDateTime.now().plusDays(3));

        Transport t4 = new Transport("Cab", "Airport Cab", "Wanderly Cabs",
                "Delhi Airport", "Delhi City", 800,
                "https://images.unsplash.com/photo-1503376780353-7e6692767b70?w=800",
                LocalDateTime.now().plusHours(5));

        transportRepository.saveAll(List.of(t1, t2, t3, t4));
        return "🚗 Transport data seeded successfully!";
    }
}

