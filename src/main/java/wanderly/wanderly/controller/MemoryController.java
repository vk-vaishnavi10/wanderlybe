package wanderly.wanderly.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Base64;

@RestController
@RequestMapping("/api/memories")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class MemoryController {

    private final List<Map<String, String>> memoryList = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping
    public List<Map<String, String>> getMemories() {
        return memoryList;
    }

    // 🪶 Upload Memory with Emotion Tagging
    @PostMapping("/upload")
    public Map<String, String> uploadMemory(
            @RequestParam("caption") String caption,
            @RequestParam("file") MultipartFile file) throws IOException {

        Map<String, String> memory = new HashMap<>();
        String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

        String emotion = detectEmotion(caption);
        String color = getColorForEmotion(emotion);

        memory.put("caption", caption);
        memory.put("imageUrl", "data:image/jpeg;base64," + base64Image);
        memory.put("date", LocalDate.now().format(formatter));
        memory.put("emotion", emotion);
        memory.put("color", color);

        memoryList.add(memory);
        return memory;
    }

    // 🧠 Simple AI-like Emotion Detection
    private String detectEmotion(String text) {
        text = text.toLowerCase();
        if (text.contains("happy") || text.contains("joy") || text.contains("sunny") || text.contains("smile"))
            return "Happy";
        if (text.contains("calm") || text.contains("peace") || text.contains("relax") || text.contains("quiet"))
            return "Calm";
        if (text.contains("sad") || text.contains("cry") || text.contains("miss") || text.contains("alone"))
            return "Nostalgic";
        if (text.contains("excited") || text.contains("love") || text.contains("amazing") || text.contains("fun"))
            return "Excited";
        if (text.contains("grateful") || text.contains("thank") || text.contains("blessed"))
            return "Grateful";
        return "Neutral";
    }

    private String getColorForEmotion(String emotion) {
        switch (emotion) {
            case "Happy": return "#FFD966";
            case "Calm": return "#AEEEEE";
            case "Nostalgic": return "#B5C7E8";
            case "Excited": return "#FFC1CC";
            case "Grateful": return "#F9E79F";
            default: return "#FFD700";
        }
    }

    // 🕰️ Time Capsule Endpoint
    @GetMapping("/timecapsule")
    public List<Map<String, String>> getAnniversaryMemories() {
        List<Map<String, String>> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Map<String, String> memory : memoryList) {
            String dateStr = memory.get("date");
            if (dateStr != null && !dateStr.isEmpty()) {
                LocalDate memoryDate = LocalDate.parse(dateStr, formatter);
                if (memoryDate.plusYears(1).isEqual(today)) {
                    result.add(memory);
                }
            }
        }
        return result;
    }
}
package wanderly.wanderly.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Base64;

@RestController
@RequestMapping("/api/memories")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class MemoryController {

    private final List<Map<String, String>> memoryList = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping
    public List<Map<String, String>> getMemories() {
        return memoryList;
    }

    // 🪶 Upload Memory with Emotion Tagging
    @PostMapping("/upload")
    public Map<String, String> uploadMemory(
            @RequestParam("caption") String caption,
            @RequestParam("file") MultipartFile file) throws IOException {

        Map<String, String> memory = new HashMap<>();
        String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

        String emotion = detectEmotion(caption);
        String color = getColorForEmotion(emotion);

        memory.put("caption", caption);
        memory.put("imageUrl", "data:image/jpeg;base64," + base64Image);
        memory.put("date", LocalDate.now().format(formatter));
        memory.put("emotion", emotion);
        memory.put("color", color);

        memoryList.add(memory);
        return memory;
    }

    // 🧠 Simple AI-like Emotion Detection
    private String detectEmotion(String text) {
        text = text.toLowerCase();
        if (text.contains("happy") || text.contains("joy") || text.contains("sunny") || text.contains("smile"))
            return "Happy";
        if (text.contains("calm") || text.contains("peace") || text.contains("relax") || text.contains("quiet"))
            return "Calm";
        if (text.contains("sad") || text.contains("cry") || text.contains("miss") || text.contains("alone"))
            return "Nostalgic";
        if (text.contains("excited") || text.contains("love") || text.contains("amazing") || text.contains("fun"))
            return "Excited";
        if (text.contains("grateful") || text.contains("thank") || text.contains("blessed"))
            return "Grateful";
        return "Neutral";
    }

    private String getColorForEmotion(String emotion) {
        switch (emotion) {
            case "Happy": return "#FFD966";
            case "Calm": return "#AEEEEE";
            case "Nostalgic": return "#B5C7E8";
            case "Excited": return "#FFC1CC";
            case "Grateful": return "#F9E79F";
            default: return "#FFD700";
        }
    }

    // 🕰️ Time Capsule Endpoint
    @GetMapping("/timecapsule")
    public List<Map<String, String>> getAnniversaryMemories() {
        List<Map<String, String>> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Map<String, String> memory : memoryList) {
            String dateStr = memory.get("date");
            if (dateStr != null && !dateStr.isEmpty()) {
                LocalDate memoryDate = LocalDate.parse(dateStr, formatter);
                if (memoryDate.plusYears(1).isEqual(today)) {
                    result.add(memory);
                }
            }
        }
        return result;
    }
}
