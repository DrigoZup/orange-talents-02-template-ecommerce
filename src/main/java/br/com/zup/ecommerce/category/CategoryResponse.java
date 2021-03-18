package br.com.zup.ecommerce.category;

public class CategoryResponse {

    private String name;

    private Category motherCategory;
    
    public CategoryResponse(Category category) {
        this.name = category.getName();
        this.motherCategory = category.getMother();
    }

    public String getName() {
        return name;
    }

    public Category getMotherCategory() {
        return motherCategory;
    }
}