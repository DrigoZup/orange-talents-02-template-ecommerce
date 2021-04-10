package br.com.zup.ecommerce.purchase;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
public class Invoice implements SuccessfullPurchaseEvent {

    @Override
    public void process(Purchase purchase) {
        Assert.isTrue(purchase.successfullyProcess(),
                "purchase successfully finished" + purchase);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request =
                Map.of("idPurchase", purchase.getId(), "idConsumer", purchase.getConsumer().getId());

        restTemplate.postForEntity("http://localhost:8080/invoice", request, String.class);
    }

}
