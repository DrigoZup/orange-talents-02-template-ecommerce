package br.com.zup.ecommerce.product;

import static br.com.zup.ecommerce.general.ConstantResponse.FIELD_CANNOT_BE_NULL;
import static br.com.zup.ecommerce.general.ConstantResponse.QUANTITY_CANNOT_BE_LESS_THEN_ZERO;
import static java.time.LocalDate.now;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.util.Assert;
import br.com.zup.ecommerce.category.Category;
import br.com.zup.ecommerce.opinion.Opinion;
import br.com.zup.ecommerce.product.attribute.AttributeProduct;
import br.com.zup.ecommerce.product.attribute.AttributeRequest;
import br.com.zup.ecommerce.product.images.ImageProduct;
import br.com.zup.ecommerce.question.Question;
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

    @OneToMany(mappedBy = "product")
    @OrderBy("title asc")
    private SortedSet<Question> questions = new TreeSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<Opinion> opinions = new HashSet<>();


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
        this.attributes.addAll(attributes.stream().map(attribute -> attribute.convertToEntity(this))
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

    public ProductOpinion getOpinions() {
        return new ProductOpinion(this.opinions);

    }

    public Set<ImageProduct> getImages() {
        return images;
    }

    public SortedSet<Question> getQuestions() {
        return questions;
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

    public <T> Set<T> mapperAttributes(Function<AttributeProduct, T> mapperFunction) {
        return this.attributes.stream().map(mapperFunction).collect(Collectors.toSet());
    }

    public <T> Set<T> mapperImages(Function<ImageProduct, T> mapperFunction) {
        return this.images.stream().map(mapperFunction).collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapperQuestions(
            Function<Question, T> mapperFunction) {
        return this.questions.stream().map(mapperFunction)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public void relateImages(Set<String> links) {
        Set<ImageProduct> images = links.stream().map(link -> new ImageProduct(this, link))
                .collect(Collectors.toSet());

        this.images.addAll(images);
    }

    public boolean decreasesStock(int quantity) {
        Assert.isTrue(quantity > 0, QUANTITY_CANNOT_BE_LESS_THEN_ZERO + quantity);

        if (quantity <= this.quantity) {
            this.quantity -= quantity;
            return true;
        }
        return false;
    }

}
