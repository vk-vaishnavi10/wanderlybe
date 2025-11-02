package wanderly.wanderly.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dining_reservations")
public class DiningReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime reservationTime;
    private int pax;

    @ManyToOne
    @JoinColumn(name = "dining_id", nullable = false)
    private Dining dining;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getReservationTime() { return reservationTime; }
    public void setReservationTime(LocalDateTime reservationTime) { this.reservationTime = reservationTime; }

    public int getPax() { return pax; }
    public void setPax(int pax) { this.pax = pax; }

    public Dining getDining() { return dining; }
    public void setDining(Dining dining) { this.dining = dining; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}

