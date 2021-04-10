package br.com.zup.ecommerce.product;

import java.time.LocalDate;
import java.util.Collection;
import br.com.zup.ecommerce.category.Category;
import br.com.zup.ecommerce.product.attribute.AttributeProduct;

public class ProductResponse {

    private String name;
    private Integer quantity;
    private String description;
    private Double price;
    private Category category;
    private Collection<AttributeProduct> attributes;
    private LocalDate createAct;
    
    public ProductResponse(Product product) {
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.attributes = product.getAttributes();
        this.createAct = product.getCreateAct();
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Collection<AttributeProduct> getAttributes() {
        return attributes;
    }

    public LocalDate getCreateAct() {
        return createAct;
    }
    
}
