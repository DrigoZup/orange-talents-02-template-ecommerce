package br.com.zup.ecommerce.question;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/questions")
@Transactional
public class QuestionController {

    @PersistenceContext
    EntityManager manager;
    
    @Autowired
    Emails emails;
    
    @PostMapping
    public QuestionResponse makeQuestion(@Valid @RequestBody QuestionRequest request) {
        Question question = request.converterToEntity(manager);
        manager.persist(question);

        emails.newQuestion(question);
        
        return new QuestionResponse(question);
    }
}
