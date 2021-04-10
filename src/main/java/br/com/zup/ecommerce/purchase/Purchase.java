package br.com.zup.ecommerce.purchase;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.purchase.gateway.ResponseGatewayPayment;
import br.com.zup.ecommerce.purchase.transaction.Transaction;
import br.com.zup.ecommerce.user.User;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne
    private Product saledProduct;

    private int quantity;
    
    @ManyToOne
    @Valid
    private User consumer;
    @Enumerated
    private GatewayPayment gateway;
    
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.MERGE)
    private Set<Transaction> transactions = new HashSet<>();
    
    public Purchase(Product saledProduct, int quantity, User consumer,
            GatewayPayment gateway) {
        this.saledProduct = saledProduct;
        this.quantity = quantity;
        this.consumer = consumer;
        this.gateway = gateway;
    }

    public Long getId() {
        return id;
    }

    public Product getSaledProduct() {
        return saledProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public User getConsumer() {
        return consumer;
    }

    public GatewayPayment getGateway() {
        return gateway;
    }
    
    
    public String urlRedirect(
            UriComponentsBuilder uriComponentsBuilder) {
        return this.gateway.criateUrlResponse(this, uriComponentsBuilder);
    }
    
    public void addTransaction(@Valid ResponseGatewayPayment request) {
        Transaction newTransaction = request.toTransaction(this);
        
        Assert.state(!this.transactions.contains(newTransaction),
                "this transaction already's executed"
                        + newTransaction);
        Assert.state(transactionsSuccessfullyFinisheds().isEmpty(),"Purchase finished Successfully");

        this.transactions.add(newTransaction);
    }

    private Set<Transaction> transactionsSuccessfullyFinisheds() {
            Set<Transaction> transactionsFinisheds = this.transactions.stream()
                .filter(Transaction::successfullyFinished)
                .collect(Collectors.toSet());
        
        Assert.isTrue(transactionsFinisheds.size() <= 1,"Error"+this.id);
        
        return transactionsFinisheds;
    }

    public boolean successfullyProcess() {
        return !transactionsSuccessfullyFinisheds().isEmpty();
    }

}
