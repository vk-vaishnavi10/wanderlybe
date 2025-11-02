package wanderly.wanderly.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

@Entity
@Table(name = "flight_bookings")
public class FlightBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String passengers;

    @JsonFormat(pattern = "yyyy-MM-dd") // ✅ ensures proper date parsing from frontend
    private LocalDate travelDate;

    private String flightName;
    private String route;
    private double amount;

    @PrePersist
    public void setDefaults() {
        if (this.travelDate == null) {
            this.travelDate = LocalDate.now();
        }
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassengers() { return passengers; }
    public void setPassengers(String passengers) { this.passengers = passengers; }

    public LocalDate getTravelDate() { return travelDate; }
    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }

    public String getFlightName() { return flightName; }
    public void setFlightName(String flightName) { this.flightName = flightName; }

    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}

