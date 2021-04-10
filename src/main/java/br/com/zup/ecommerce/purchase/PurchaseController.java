package br.com.zup.ecommerce.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zup.ecommerce.general.Emails;

@RestController
@RequestMapping("/purchases")
@Transactional
public class PurchaseController {

    
    @PersistenceContext
    EntityManager manager;
    
    @Autowired
    Emails emails;
    
    public String sale(@RequestBody @Valid PurchaseRequest request, UriComponentsBuilder uri) throws BindException{
        Purchase purchase = request.converterToEntity(manager);
        
        boolean decreases = purchase.getSaledProduct().decreasesStock(purchase.getQuantity());
        
        if (!decreases) {
            BindException stockError = new BindException(request,"purchaseRequest");
            stockError.reject(null, "This product is unavalible");

            throw stockError;
        }
        
        manager.persist(purchase);
        emails.newPurchase(purchase);
        return purchase.urlRedirect(uri);
    }
}
