package Default;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient createWebClient() {
        // Increase the maximum buffer size to accommodate larger responses
        int size = -1; // Unlimited buffer size

        ExchangeStrategies strategies = ExchangeStrategies.builder()
            .codecs(this::customizeCodecs)
            .build();

        return WebClient.builder()
            .exchangeStrategies(strategies)
            .build();
    }

    private void customizeCodecs(ClientCodecConfigurer configurer) {
        configurer.defaultCodecs().maxInMemorySize(-1); // Set unlimited buffer size
    }
}


