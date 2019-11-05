package apap.tutorial.gopud.service;

import reactor.core.publisher.Mono;

public interface SpoonacularRestService {
    Mono<String> searchRecipeGermanyExcludedIngredient(String ingredient);
}
