package br.com.zup.ecommerce.product.details;

import br.com.zup.ecommerce.product.attribute.AttributeProduct;

public class ProductAttributeDetails {

    private String name;
    private String description;

    public ProductAttributeDetails(AttributeProduct attribute) {
        this.name = attribute.getName();
        this.description = attribute.getDescription();
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }

}
