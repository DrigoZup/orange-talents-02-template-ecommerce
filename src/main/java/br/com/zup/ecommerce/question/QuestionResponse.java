package br.com.zup.ecommerce.question;

import java.time.LocalDate;
import br.com.zup.ecommerce.product.ProductResponse;
import br.com.zup.ecommerce.user.UserResponse;

public class QuestionResponse {

    private Long id;
    
    private String title;
    
    private LocalDate createAct;
    
    private UserResponse consumer;
    
    private ProductResponse questionPorduct;
    
    public QuestionResponse(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.createAct = question.getCreateAct();
        this.consumer = new UserResponse(question.getConsumer());
        this.questionPorduct = new ProductResponse(question.getProduct());
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

    public UserResponse getConsumer() {
        return consumer;
    }

    public ProductResponse getQuestionPorduct() {
        return questionPorduct;
    }
    
}
