package br.com.zup.ecommerce.purchase.gateway;

import br.com.zup.ecommerce.purchase.Purchase;
import br.com.zup.ecommerce.purchase.transaction.Transaction;

public interface ResponseGatewayPayment {

    Transaction toTransaction(Purchase purchase);
}
