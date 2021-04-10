package br.com.zup.ecommerce.purchase;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewPurchaseEvent implements SuccessfullPurchaseEvent{

    @Autowired
    private Set<SuccessfullPurchaseEvent> successfullPurchaseEvent;

    public void process(Purchase purchase) {
        if(purchase.successfullyProcess()) {
            successfullPurchaseEvent.forEach(evento -> evento.process(purchase));
        } 
        else {
            //failed events
        }       
    }

}
