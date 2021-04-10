package br.com.zup.ecommerce.purchase.gateway;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import br.com.zup.ecommerce.purchase.Purchase;
import br.com.zup.ecommerce.purchase.transaction.StatusTransaction;
import br.com.zup.ecommerce.purchase.transaction.Transaction;

public class ResponsePaypalRequest implements ResponseGatewayPayment{

    @Min(0)
    @Max(1)
    private int status;
    @NotBlank
    private String idTransaction;

    public ResponsePaypalRequest(int status, String idTransaction) {
        this.status = status;
        this.idTransaction = idTransaction;
    }

    public Transaction toTransaction(Purchase purchase) {
        StatusTransaction status = this.status == 0 ? StatusTransaction.error
                : StatusTransaction.success;
        
        return new Transaction(status, idTransaction, purchase);
    }

}
