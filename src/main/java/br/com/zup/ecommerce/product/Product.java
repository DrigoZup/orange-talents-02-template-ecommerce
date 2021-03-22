package br.com.zup.ecommerce.product;

import static java.time.LocalDate.now;
import static br.com.zup.ecommerce.general.ConstantResponse.FIELD_CANNOT_BE_NULL;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.util.Assert;
import br.com.zup.ecommerce.category.Category;
import br.com.zup.ecommerce.product.attribute.AttributeProduct;
import br.com.zup.ecommerce.product.attribute.AttributeRequest;
import br.com.zup.ecommerce.product.images.ImageProduct;
import br.com.zup.ecommerce.user.User;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;
    private Integer quantity;
    private String description;
    private Double price;
    @NotNull(message = FIELD_CANNOT_BE_NULL)
    @Valid
    @ManyToOne
    private Category category;
    
    @ManyToOne
    @Valid
    private User owner;
    
    private LocalDate createAct;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<AttributeProduct> attributes = new HashSet<>();
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<ImageProduct> images = new HashSet<>();

    @Deprecated
    public Product() {}

    public Product(String name, Integer quantity, String description, Double price,
            Category category, User owner, Collection<AttributeRequest> attributes) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.category = category;
        this.owner = owner;
        this.createAct = now();
        this.attributes.addAll(attributes.stream().map(feature -> feature.convertToEntity(this))
                .collect(Collectors.toSet()));
        
        Assert.isTrue(attributes.size() >= 3, "The product needs at least 3 attributes");
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
    
    public User getOwner() {
        return owner;
    }

     public LocalDate getCreateAct() {
        return createAct;
    }

     public Collection<AttributeProduct> getAttributes() {
         return attributes;
     }
     
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public void relateImages(Set<String> links) {
        Set<ImageProduct> images = links.stream()
                .map(link -> new ImageProduct(this, link))
                .collect(Collectors.toSet());

        this.images.addAll(images);
    }

}
