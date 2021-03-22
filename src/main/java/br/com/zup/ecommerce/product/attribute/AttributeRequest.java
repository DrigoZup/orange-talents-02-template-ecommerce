package br.com.zup.ecommerce.product.attribute;

import br.com.zup.ecommerce.product.Product;

public class AttributeRequest {

    private String name;
    
    private String description;

    public AttributeRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public AttributeProduct convertToEntity(Product product) {
        return new AttributeProduct(name, description, product);
    }
}
