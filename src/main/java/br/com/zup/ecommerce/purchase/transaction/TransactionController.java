package br.com.zup.ecommerce.purchase.transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.ecommerce.purchase.NewPurchaseEvent;
import br.com.zup.ecommerce.purchase.Purchase;
import br.com.zup.ecommerce.purchase.PurchaseResponse;
import br.com.zup.ecommerce.purchase.gateway.ResponseGatewayPayment;
import br.com.zup.ecommerce.purchase.gateway.ResponsePagSeguroRequest;
import br.com.zup.ecommerce.purchase.gateway.ResponsePaypalRequest;

@RestController
@RequestMapping("/purchases")
@Transactional
public class TransactionController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private NewPurchaseEvent newPurchaseEvent;

    @PostMapping(path = "/pagseguro")
    public PurchaseResponse processamentoPagSeguro(@PathVariable Long idCompra, @Valid ResponsePagSeguroRequest request) {
        return process(idCompra, request);
    }
    
    @PostMapping(path = "/paypal")
    public PurchaseResponse processamentoPaypal(@PathVariable Long idCompra, @Valid ResponsePaypalRequest request) {
        return process(idCompra, request);
    }
    
    private PurchaseResponse process(Long idPurchase,ResponseGatewayPayment responseGatewayPayment) {
        Purchase purchase = manager.find(Purchase.class, idPurchase);
        purchase.addTransaction(responseGatewayPayment);      
        manager.merge(purchase);      
        newPurchaseEvent.process(purchase);
        
        return new PurchaseResponse(purchase);       
    }
}
