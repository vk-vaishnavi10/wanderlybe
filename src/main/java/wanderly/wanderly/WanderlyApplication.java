package wanderly.wanderly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "wanderly.wanderly")
@EntityScan(basePackages = "wanderly.wanderly.entity")
@EnableJpaRepositories(basePackages = "wanderly.wanderly.repository")
public class WanderlyApplication {
    public static void main(String[] args) {
        SpringApplication.run(WanderlyApplication.class, args);
    }
}
