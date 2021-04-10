package br.com.zup.ecommerce.purchase;

import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.user.User;

public class PurchaseResponse {

    private Long id;
    
    private int quantity;
    
    private User consumer;
    
    private Product product;
    
    private GatewayPayment gateway;
    
    public PurchaseResponse(Purchase purchase) {
        this.id = purchase.getId();
        this.quantity = purchase.getQuantity();
        this.consumer = purchase.getConsumer();
        this.product = purchase.getSaledProduct();
        this.gateway = purchase.getGateway();
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public User getConsumer() {
        return consumer;
    }

    public Product getProduct() {
        return product;
    }

    public GatewayPayment getGateway() {
        return gateway;
    }
    
}
