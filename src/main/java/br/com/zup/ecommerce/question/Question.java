package br.com.zup.ecommerce.question;

import static java.time.LocalDate.now;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.user.User;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    private LocalDate createAct;
    
    @OneToOne
    private User consumer;
    
    @OneToOne
    private Product product;

    public Question(String title, User consumer, Product product) {
        this.title = title;
        this.createAct = now();
        this.consumer = consumer;
        this.product = product;
    }
    
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreateAct() {
        return createAct;
    }

    public User getConsumer() {
        return consumer;
    }
    
    public Product getProduct() {
        return product;
    }

}
