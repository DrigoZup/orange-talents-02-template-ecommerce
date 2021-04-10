package br.com.zup.ecommerce.opinion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.user.User;

@Entity
public class Opinion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    private String title;

    private String description;
    
    @ManyToOne
    private User consumer;
    
    @ManyToOne
    private Product product;

    public Opinion(Integer rating, String title, String description, User consumer,
            Product product) {
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.consumer = consumer;
        this.product = product;
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

    public User getConsumer() {
        return consumer;
    }

    public Product getProduct() {
        return product;
    }
    
}
