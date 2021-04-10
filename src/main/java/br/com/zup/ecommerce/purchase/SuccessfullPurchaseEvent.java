package br.com.zup.ecommerce.purchase;

public interface SuccessfullPurchaseEvent {

    void process(Purchase purchase);
}
