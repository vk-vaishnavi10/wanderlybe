package wanderly.wanderly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.User;
import wanderly.wanderly.repository.UserRepository;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Register User
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // check duplicates
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists"));

        if (userRepository.findByPhone(user.getPhone()).isPresent())
            return ResponseEntity.badRequest().body(Map.of("error", "Phone number already exists"));

        // generate Wander ID
        String wanderId = "WAND-" + (100000 + new Random().nextInt(900000));
        user.setWanderId(wanderId);

        userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully!");
        response.put("wanderId", wanderId);
        response.put("user", user);
        return ResponseEntity.ok(response);
    }

    // ✅ Login User (via Wander ID / Email / Phone)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String identifier = credentials.get("wanderId");
        String password = credentials.get("password");

        if (identifier == null || password == null)
            return ResponseEntity.badRequest().body(Map.of("error", "Missing credentials"));

        Optional<User> userOpt = userRepository.findByEmail(identifier);
        if (userOpt.isEmpty()) userOpt = userRepository.findByPhone(identifier);
        if (userOpt.isEmpty()) userOpt = userRepository.findByWanderId(identifier);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return ResponseEntity.ok(Map.of(
                        "message", "Login successful!",
                        "user", user
                ));
            }
        }

        return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
    }

    // ✅ Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update user info
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFullName(updatedUser.getFullName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    user.setPhone(updatedUser.getPhone());
                    userRepository.save(user);
                    return ResponseEntity.ok(Map.of("message", "User updated successfully"));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id))
            return ResponseEntity.notFound().build();

        userRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}
