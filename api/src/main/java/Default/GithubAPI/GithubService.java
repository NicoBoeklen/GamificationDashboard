package Default.GithubAPI;

import Default.Commit.Commit;
import Default.GithubRepo.GithubRepo;
import Default.User.User;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GithubService {
    private final WebClient webClient;

    public GithubService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    public Flux<User> getContributors(String owner, String repo) {
        return this.webClient.get()
            .uri("/repos/{owner}/{repo}/contributors", owner, repo)
            .retrieve()
            .bodyToFlux(User.class);
    }

    public Mono<GithubRepo> getRepository(String owner, String repo) {
        return this.webClient.get()
            .uri("/repos/{owner}/{repo}", owner, repo)
            .retrieve()
            .bodyToMono(GithubRepo.class);
    }

    public Flux<Commit> getCommits(String owner, String repo) {
        String url = String.format("/repos/%s/%s/commits", owner, repo);
        return getCommitsRecursively(url);
    }

    private Flux<Commit> getCommitsRecursively(String url) {
        return webClient.get()
            .uri(url)
            .retrieve()
            //.onStatus(HttpStatus::isError, clientResponse -> Mono.error(new RuntimeException("Failed to fetch commits")))
            .bodyToFlux(Commit.class)
            .collectList()
            .flatMapMany(commits -> {
                return getNextPageUrl(url)
                    .flatMapMany(nextUrl -> Flux.fromIterable(commits).concatWith(getCommitsRecursively(nextUrl)))
                    .switchIfEmpty(Flux.fromIterable(commits));
            });
    }

    private Mono<String> getNextPageUrl(String url) {
        return webClient.get()
            .uri(url)
            .exchangeToMono(response -> {
                HttpHeaders headers = response.headers().asHttpHeaders();
                List<String> linkHeaders = headers.get(HttpHeaders.LINK);
                if (linkHeaders == null || linkHeaders.isEmpty()) {
                    return Mono.empty();
                }

                Pattern pattern = Pattern.compile("<(.*?)>;\\s*rel=\"next\"");
                for (String header : linkHeaders) {
                    Matcher matcher = pattern.matcher(header);
                    if (matcher.find()) {
                        return Mono.just(matcher.group(1));
                    }
                }

                return Mono.empty();
            });
    }
}

