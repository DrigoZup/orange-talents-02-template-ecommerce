package br.com.zup.ecommerce.opinion;

public class OpinionResponse {

    private Integer rating;

    private String title;

    private String description;

    public OpinionResponse(Opinion opinion) {
        this.rating = opinion.getRating();
        this.title = opinion.getTitle();
        this.description = opinion.getDescription();
    }

    public Integer getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    
    
}
