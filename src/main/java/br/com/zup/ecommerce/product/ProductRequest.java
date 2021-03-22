package br.com.zup.ecommerce.product;

import static br.com.zup.ecommerce.general.ConstantResponse.FIELD_CANNOT_BE_NULL;
import static br.com.zup.ecommerce.general.ConstantResponse.FIELD_CANNOT_BE_BLANK;
import static br.com.zup.ecommerce.general.ConstantResponse.UNAVALIBLE_DATA;
import static br.com.zup.ecommerce.general.ConstantResponse.UNAVALIBLE_FORMAT;

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

    @NotBlank(message = FIELD_CANNOT_BE_BLANK)
    @UniqueValue(domainClass = Product.class,fieldName = "name")
    private String name;
    @Positive
    @NotNull(message = FIELD_CANNOT_BE_NULL)
    private Integer quantity;
    @NotBlank
    @Size(max = 1000, message = UNAVALIBLE_FORMAT)
    private String description;
    @NotNull(message = FIELD_CANNOT_BE_NULL)
    @Positive(message = UNAVALIBLE_DATA)
    private Double price;
    
    private User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
    @NotNull(message = FIELD_CANNOT_BE_NULL)
    private Long idCategory;
    
    @Size(min = 3, message = UNAVALIBLE_DATA)
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
