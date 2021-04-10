package br.com.zup.ecommerce.purchase.transaction;

import static br.com.zup.ecommerce.purchase.transaction.StatusTransaction.success;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.zup.ecommerce.purchase.Purchase;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private StatusTransaction status;
    @NotBlank
    private String idTransactionGateway;
    @NotNull
    private LocalDateTime moment;
    @ManyToOne
    private @NotNull @Valid Purchase purchase;
    
    @Deprecated
    public Transaction() {

    }

    public Transaction(StatusTransaction status,
            String idTransactionGateway, Purchase purchase) {
        this.status = status;
        this.idTransactionGateway = idTransactionGateway;
        this.purchase = purchase;
        this.moment = LocalDateTime.now();
    }
    
    public boolean successfullyFinished() {
        return this.status.equals(success);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idTransactionGateway == null) ? 0 : idTransactionGateway.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transaction other = (Transaction) obj;
        if (idTransactionGateway == null) {
            if (other.idTransactionGateway != null)
                return false;
        } else if (!idTransactionGateway.equals(other.idTransactionGateway))
            return false;
        return true;
    }
}
