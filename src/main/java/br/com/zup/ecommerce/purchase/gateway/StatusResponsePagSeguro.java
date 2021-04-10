package br.com.zup.ecommerce.purchase.gateway;

import br.com.zup.ecommerce.purchase.transaction.StatusTransaction;

public enum StatusResponsePagSeguro {

    SUCCESS,ERROR;

    public StatusTransaction normalizas() {
        if(this.equals(SUCCESS)) {
            return StatusTransaction.success;
        }
        
        return StatusTransaction.error;
    }
}
