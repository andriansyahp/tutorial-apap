package apap.tutorial.gopud.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpoonacularDetail {
    @JsonProperty("apiKey")
    private String apiKey;

    @JsonProperty("query")
    private String query;

    @JsonProperty("cuisine")
    private String cuisine;

    @JsonProperty("diet")
    private String diet;

    @JsonProperty("excludeIngredients")
    private String excludeIngredients;

    @JsonProperty("intolerances")
    private String intolerances;

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("limitLicense")
    private Boolean limitLicense;

    @JsonProperty("instructionsRequired")
    private Boolean instructionsRequired;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getExcludeIngredients() {
        return excludeIngredients;
    }

    public void setExcludeIngredients(String excludeIngredients) {
        this.excludeIngredients = excludeIngredients;
    }

    public String getIntolerances() {
        return intolerances;
    }

    public void setIntolerances(String intolerances) {
        this.intolerances = intolerances;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getLimitLicense() {
        return limitLicense;
    }

    public void setLimitLicense(Boolean limitLicense) {
        this.limitLicense = limitLicense;
    }

    public Boolean getInstructionsRequired() {
        return instructionsRequired;
    }

    public void setInstructionsRequired(Boolean instructionsRequired) {
        this.instructionsRequired = instructionsRequired;
    }
}
