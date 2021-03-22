package br.com.zup.ecommerce.product.attribute;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import br.com.zup.ecommerce.product.Product;

@Entity
public class AttributeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;
    private  String description;
    @ManyToOne
    @Valid
    private Product product;
    
    @Deprecated
    public AttributeProduct() {
    }

    public AttributeProduct(String name, String description, Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Product getProduct() {
        return product;
    }
}
