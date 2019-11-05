package apap.tutorial.gopud.service;

import apap.tutorial.gopud.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class SpoonacularRestServiceImpl implements SpoonacularRestService {
    private final WebClient webClient;

    public SpoonacularRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.spoonacularUrl).build();
    }

    @Override
    public Mono<String> searchRecipeGermanyExcludedIngredient(String ingredient){
        return this.webClient.get().uri(uriBuilder -> uriBuilder
                .queryParam("apiKey", Setting.spoonacularApiKey)
                .queryParam("excludeIngredients", ingredient)
                .queryParam("cuisine", "german")
                .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
