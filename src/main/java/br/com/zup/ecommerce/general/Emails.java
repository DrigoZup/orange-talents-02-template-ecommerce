package br.com.zup.ecommerce.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.zup.ecommerce.purchase.Purchase;
import br.com.zup.ecommerce.question.Question;

@Component
public class Emails {

    @Autowired
    Mailer mailer;

    public void newQuestion(Question question) {
        String consumer = question.getConsumer().getLogin();
        String seller = question.getProduct().getOwner().getLogin();

        mailer.send("<html>...</html>", "New Question...", consumer,
                "novapergunta@nossomercadolivre.com", seller);
    }

    public void newPurchase(Purchase purchase) {
        String consumer = purchase.getConsumer().getLogin();
        String seller = purchase.getSaledProduct().getOwner().getLogin();
        mailer.send("new purchase..." + purchase, "You have a new purchase", consumer,
                "purchase@ecommerce.com", seller);
    }
}
