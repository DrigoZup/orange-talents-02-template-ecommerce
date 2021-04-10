package br.com.zup.ecommerce.purchase;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
public class Ranking implements SuccessfullPurchaseEvent {

    @Override
    public void process(Purchase purchase) {
        Assert.isTrue(purchase.successfullyProcess(),
                "purchase failed" + purchase);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idPurchase", purchase.getId(), "idSeller",
                purchase.getSaledProduct().getOwner().getId());

        restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);


    }

}
