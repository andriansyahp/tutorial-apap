package apap.tutorial.gopud.restcontroller;

import apap.tutorial.gopud.service.SpoonacularRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/recipe")
public class SpoonacularRestController {
    @Autowired
    private SpoonacularRestService spoonacularRestService;

    @GetMapping("/search")
    private Mono<String> getRecipeByGermanyAndExcludedIngredientQuery(
            @RequestParam("excludeIngredients") String ingredient
    ) {
        return spoonacularRestService.searchRecipeGermanyExcludedIngredient(ingredient);
    }
}
