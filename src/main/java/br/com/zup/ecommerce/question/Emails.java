package br.com.zup.ecommerce.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Emails {

    @Autowired
    Mailer mailer;

    public void newQuestion(Question question) {
        String consumer = question.getConsumer().getLogin();
        String ownerProduct = question.getProduct().getOwner().getLogin();
        
        mailer.send("<html>...</html>","New Question...",consumer,"novapergunta@nossomercadolivre.com", ownerProduct);
    }
}
