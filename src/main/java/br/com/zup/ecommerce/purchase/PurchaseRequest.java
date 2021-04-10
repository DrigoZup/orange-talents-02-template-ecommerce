package br.com.zup.ecommerce.purchase;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.user.User;
import br.com.zup.ecommerce.user.current.AuthUser;

public class PurchaseRequest {

    @Positive
    private Integer quantity;
    @NotNull
    private Long idProduct;
    
    private User user;
    
    @NotNull
    private GatewayPayment gateway;
    
    public PurchaseRequest(Integer quantity,Long idProduct, GatewayPayment gateway) {
        this.quantity = quantity;
        this.idProduct = idProduct;
        this.gateway = gateway;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public GatewayPayment getGateway() {
        return gateway;
    }
    
    public Purchase converterToEntity(EntityManager manager) {
        Product product = manager.find(Product.class, idProduct);
        AuthUser consumer = new AuthUser(user);
        return new Purchase(product, quantity, consumer.get(), gateway);
    }
    
}
