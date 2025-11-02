package wanderly.wanderly.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "${app.cors.allowed-origins}")
public class AiCompanionController {

    private final WebClient wanderlyClient;

    public AiCompanionController(@Qualifier("wanderlyClient") WebClient wanderlyClient) {
        this.wanderlyClient = wanderlyClient;
    }

    // Data Models
    public record ChatMessage(String role, String content) {}
    public record ChatRequest(List<ChatMessage> messages, Double temperature) {}

    @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map> chat(@RequestBody(required = false) ChatRequest request) {
        if (request == null || request.messages() == null || request.messages().isEmpty()) {
            return Mono.just(Map.of("error", "❌ 'messages' is required"));
        }

        Map<String, Object> body = Map.of(
                // 💡 Using free Groq LLaMA3 model
                "model", "llama3-8b-8192",
                "temperature", request.temperature() == null ? 0.7 : request.temperature(),
                "messages", request.messages()
        );

        return wanderlyClient.post()
                .uri("/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(10))
                                .maxBackoff(Duration.ofSeconds(30))
                                .filter(e -> e instanceof WebClientResponseException.TooManyRequests)
                )
                .onErrorResume(e -> {
                    String message = e.getMessage();
                    if (message != null && message.contains("429")) {
                        return Mono.just(Map.of("error", "⚠️ Too many requests — please wait and try again."));
                    } else if (message != null && message.contains("401")) {
                        return Mono.just(Map.of("error", "🔑 Invalid or missing API key."));
                    } else {
                        return Mono.just(Map.of("error", "⚠️ Wanderly AI request failed: " + message));
                    }
                });
    }
}


