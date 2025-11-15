package wanderly.wanderly.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import wanderly.wanderly.entity.Dining;
import wanderly.wanderly.entity.User;
import wanderly.wanderly.repository.DiningRepository;
import wanderly.wanderly.repository.UserRepository;

import java.util.List;

@Component
public class StartupSeeder {

    private final UserRepository userRepo;
    private final DiningRepository diningRepo;

    public StartupSeeder(UserRepository userRepo, DiningRepository diningRepo) {
        this.userRepo = userRepo;
        this.diningRepo = diningRepo;
    }

    @PostConstruct
    public void seedData() {
        seedUsers();
        seedDining();
    }

    private void seedUsers() {
        if (userRepo.count() == 0) {
            User u = new User();
            u.setFullName("Vaishnavi");
            u.setEmail("vaishnavi@example.com");
            u.setPassword("12345");
            u.setPhone("9876543210");
            userRepo.save(u);
            System.out.println("✅ Default user seeded successfully!");
        } else {
            System.out.println("✅ Users already exist, skipping user seed.");
        }
    }

    private void seedDining() {
        if (diningRepo.count() == 0) {
            Dining r1 = new Dining();
            r1.setName("The Royal Feast");
            r1.setCuisine("Multi-Cuisine Luxury");
            r1.setLocation("Taj Palace, Mumbai");
            r1.setDescription("Enjoy fine dining with a royal ambience and exquisite international cuisines.");
            r1.setImageUrl("https://images.unsplash.com/photo-1550966871-3ed3cdb5ed0c?w=1000");

            Dining r2 = new Dining();
            r2.setName("Bean & Brew Café");
            r2.setCuisine("Local Coffee & Pastries");
            r2.setLocation("Brigade Road, Bangalore");
            r2.setDescription("A cozy café serving artisanal coffees and freshly baked treats.");
            r2.setImageUrl("https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=1000");

            Dining r3 = new Dining();
            r3.setName("Spice Street");
            r3.setCuisine("Authentic Indian Street Food");
            r3.setLocation("Chandni Chowk, Delhi");
            r3.setDescription("Indulge in flavorful chaats, kebabs, and sweets in a lively atmosphere.");
            r3.setImageUrl("https://images.unsplash.com/photo-1551782450-a2132b4ba21d?w=1000");

            diningRepo.saveAll(List.of(r1, r2, r3));
            System.out.println("🍴 Dining table empty — seeding sample data...");
            System.out.println("✅ Dining data seeded successfully!");
        } else {
            System.out.println("✅ Dining data already present, skipping seed.");
        }
    }
}
