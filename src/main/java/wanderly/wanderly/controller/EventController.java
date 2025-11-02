package wanderly.wanderly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.Event;
import wanderly.wanderly.repository.EventRepository;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventRepository.save(event));
    }
}
