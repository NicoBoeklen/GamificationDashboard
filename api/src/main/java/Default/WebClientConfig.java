package Default;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration 
public class WebClientConfig {

        @Bean
        public WebClient createWebClient() {
            int size = 16 * 1024 * 1024; // 16MB

            ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(size))
                .build();

            return WebClient.builder()
                .exchangeStrategies(strategies)
                .build();
        }
    }


