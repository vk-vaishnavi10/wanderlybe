package wanderly.wanderly.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transport")
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // e.g., "Car", "Cab", "Bus"
    private String name; // e.g., "Sedan", "SUV", "Luxury", "Airport Cab"
    private String provider; // e.g., "Wanderly Rentals"
    private String fromCity;
    private String toCity;

    private double price; // per trip or per day

    private String imageUrl; // ✅ for frontend display

    private LocalDateTime departureTime;

    public Transport() {}

    public Transport(String type, String name, String provider, String fromCity, String toCity,
                     double price, String imageUrl, LocalDateTime departureTime) {
        this.type = type;
        this.name = name;
        this.provider = provider;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.price = price;
        this.imageUrl = imageUrl;
        this.departureTime = departureTime;
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getFromCity() { return fromCity; }
    public void setFromCity(String fromCity) { this.fromCity = fromCity; }

    public String getToCity() { return toCity; }
    public void setToCity(String toCity) { this.toCity = toCity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
}

