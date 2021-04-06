package br.com.zup.ecommerce.purchase.gateway;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.zup.ecommerce.purchase.Purchase;
import br.com.zup.ecommerce.purchase.transaction.Transaction;

public class ResponsePagSeguroRequest implements ResponseGatewayPayment{

    @NotBlank
    private String idTransaction;
    @NotNull
    private StatusResponsePagSeguro status;
    
    public ResponsePagSeguroRequest(String idTransaction,
            StatusResponsePagSeguro status) {
        super();
        this.idTransaction = idTransaction;
        this.status = status;
    }
    @Override
    public Transaction toTransaction(Purchase purchase) {
        return new Transaction(status.normalizas(), idTransaction, purchase);
    }

}
