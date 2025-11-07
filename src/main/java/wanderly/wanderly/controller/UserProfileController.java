package wanderly.wanderly.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class UserProfileController {

    private static final String UPLOAD_DIR = "uploads/profile/";
    private static final Map<String, String> userProfile = new HashMap<>();

    static {
        userProfile.put("name", "Vaishnavi 🌸");
        userProfile.put("bio", "Collecting places, not things — one memory at a time.");
        userProfile.put("photo", "https://i.pravatar.cc/150?img=14");
    }

    @GetMapping("/profile")
    public Map<String, String> getUserProfile() {
        return userProfile;
    }

    @PostMapping("/update")
    public Map<String, String> updateProfile(
            @RequestParam("name") String name,
            @RequestParam("bio") String bio,
            @RequestParam(value = "photo", required = false) MultipartFile photo
    ) {
        userProfile.put("name", name);
        userProfile.put("bio", bio);

        // Ensure directory exists
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (photo != null && !photo.isEmpty()) {
            try {
                String fileName = "profile-" + System.currentTimeMillis() + ".jpg";
                Path filePath = Paths.get(UPLOAD_DIR, fileName);

                Files.write(filePath, photo.getBytes());

                // Save file path as accessible endpoint
                String photoUrl = "http://localhost:8085/api/user/photo/" + fileName;
                userProfile.put("photo", photoUrl);

            } catch (IOException e) {
                userProfile.put("photo", "https://i.pravatar.cc/150?img=14");
            }
        }

        return userProfile;
    }

    @GetMapping("/photo/{fileName}")
    public @ResponseBody byte[] getPhoto(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        if (!Files.exists(filePath)) {
            return new byte[0];
        }
        return Files.readAllBytes(filePath);
    }
}
