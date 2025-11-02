// src/main/java/wanderly/wanderly/entity/Payment.java
package wanderly.wanderly.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String email;
    private String flightName;
    private String route;
    private double amount;
    private String status;
    private LocalDateTime paidAt;

    @PrePersist
    public void setTimestamp() {
        this.paidAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFlightName() { return flightName; }
    public void setFlightName(String flightName) { this.flightName = flightName; }

    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }
}
