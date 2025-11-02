package wanderly.wanderly.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "event_bookings")
public class EventBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long eventId;
    private String ticketType;
    private double price;

    public EventBooking() {}

    public EventBooking(Long userId, Long eventId, String ticketType, double price) {
        this.userId = userId;
        this.eventId = eventId;
        this.ticketType = ticketType;
        this.price = price;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTicketType() {
        return ticketType;
    }
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}


