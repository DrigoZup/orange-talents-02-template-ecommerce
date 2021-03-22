package br.com.zup.ecommerce.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.springframework.security.core.context.SecurityContextHolder;
import br.com.zup.ecommerce.category.Category;
import br.com.zup.ecommerce.general.UniqueValue;
import br.com.zup.ecommerce.product.attribute.AttributeRequest;
import br.com.zup.ecommerce.user.User;

public class ProductRequest {

    @NotBlank
    @UniqueValue(domainClass = Product.class,fieldName = "name")
    private String name;
    @Positive
    @NotNull
    private Integer quantity;
    @NotBlank
    @Size(max = 1000)
    private String description;
    @NotNull
    @Positive
    private Double price;
    
    private User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
    @NotNull
    private Long idCategory;
    
    @Size(min = 3)
    @Valid
    private List<AttributeRequest> attributes = new ArrayList<AttributeRequest>();
    

    public ProductRequest( String name,  Integer quantity,
             String description,
             Double price,  Long idCategory, User owner, List<AttributeRequest> attributes) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.idCategory = idCategory;
        this.attributes.addAll(attributes);
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

    public Long getIdCategory() {
        return idCategory;
    }

    public List<AttributeRequest> getAttributes() {
        return attributes;
    }
    
    public Product convertToEntity(EntityManager manager) {
        
        Category category = manager.find(Category.class, idCategory);

        return new Product(name, quantity, description, price, category, owner, attributes);
    }
    
    public Set<String> foundDuplicatedAttributes() {
        HashSet<String> equalNames = new HashSet<>();
        HashSet<String> responses = new HashSet<>();

        for (AttributeRequest feature : attributes) {
            String name = feature.getName();

            if (!equalNames.add(name)) {
                responses.add(name);
            }
        }
        return responses;
    }

}
