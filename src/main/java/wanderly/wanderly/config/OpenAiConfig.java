package wanderly.wanderly.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OpenAiConfig {
    @Value("${wanderly.api.key:dummy}")
    private String wanderlyApiKey;
    

    @Bean(name = "wanderlyClient")
    public WebClient wanderlyClient() {
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(c -> c.defaultCodecs().maxInMemorySize(4 * 1024 * 1024))
                .build();

        return WebClient.builder()
                // 🆓 Free Groq OpenAI-compatible API
                .baseUrl("https://api.groq.com/openai/v1")
                .defaultHeader("Authorization", "Bearer " + wanderlyApiKey)
                .defaultHeader("Content-Type", "application/json")
                .exchangeStrategies(strategies)
                .build();
    }
}



