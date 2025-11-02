package wanderly.wanderly.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cuisine;
    private String location;
    private double priceRange;
    private String imageUrl;

    public Restaurant() {}

    public Restaurant(String name, String cuisine, String location, double priceRange, String imageUrl) {
        this.name = name;
        this.cuisine = cuisine;
        this.location = location;
        this.priceRange = priceRange;
        this.imageUrl = imageUrl;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCuisine() { return cuisine; }
    public void setCuisine(String cuisine) { this.cuisine = cuisine; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getPriceRange() { return priceRange; }
    public void setPriceRange(double priceRange) { this.priceRange = priceRange; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
