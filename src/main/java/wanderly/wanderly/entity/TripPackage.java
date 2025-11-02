package wanderly.wanderly.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "packages")
public class TripPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private double price;

    // Optional fields — useful if you want to expand your frontend later
    private String duration;
    private String imageUrl;

    public TripPackage() {}

    public TripPackage(String title, String description, double price, String duration, String imageUrl) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.imageUrl = imageUrl;
    }

    // 🟡 Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

