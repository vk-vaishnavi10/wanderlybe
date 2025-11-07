package wanderly.wanderly.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.Base64;

@RestController
@RequestMapping("/api/memories")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class MemoryController {

    private final List<Map<String, String>> memoryList = new ArrayList<>();

    // 🖼️ Get all saved memories
    @GetMapping
    public List<Map<String, String>> getMemories() {
        return memoryList;
    }

    // 🌟 Upload memory with extra fields (caption, location, story, emotion)
    @PostMapping("/upload")
    public Map<String, String> uploadMemory(
            @RequestParam("caption") String caption,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "story", required = false) String story,
            @RequestParam(value = "emotion", required = false) String emotion,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        Map<String, String> memory = new HashMap<>();

        memory.put("caption", caption);
        memory.put("location", location != null ? location : "📍 Unknown");
        memory.put("story", story != null ? story : "Every journey leaves a story untold.");
        memory.put("emotion", emotion != null ? emotion : "💛 Joyful");
        memory.put("imageUrl", "data:image/jpeg;base64," +
                Base64.getEncoder().encodeToString(file.getBytes()));
        memory.put("date", new Date().toString());

        memoryList.add(memory);
        return memory;
    }

    // ⏳ Time Capsule (returns your last memory)
    @GetMapping("/timecapsule")
    public List<Map<String, String>> getTimeCapsule() {
        List<Map<String, String>> oldMemories = new ArrayList<>();
        if (!memoryList.isEmpty()) {
            oldMemories.add(memoryList.get(memoryList.size() - 1)); // mock old memory
        }
        return oldMemories;
    }
}
