package wanderly.wanderly.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "stay_bookings")
public class StayBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate checkInDate;
    private int nights;
    private int guests;

    // ✅ Relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // ✅ Relationship with Stay
    @ManyToOne
    @JoinColumn(name = "stay_id", nullable = false)
    private Stay stay;

    // ----- Getters & Setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }

    public int getNights() { return nights; }
    public void setNights(int nights) { this.nights = nights; }

    public int getGuests() { return guests; }
    public void setGuests(int guests) { this.guests = guests; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Stay getStay() { return stay; }
    public void setStay(Stay stay) { this.stay = stay; }
}
