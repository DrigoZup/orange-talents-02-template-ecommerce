package br.com.zup.ecommerce.purchase;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPayment {

    pagseguro {
        @Override
        String criateUrlResponse(Purchase purchase,
                UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPagseguro = uriComponentsBuilder
                    .path("/response-pagseguro/{id}")
                    .buildAndExpand(purchase.getId()).toString();

            return "pagseguro.com/" + purchase.getId() + "?redirectUrl="
                    + urlRetornoPagseguro;
        }
    },
    paypal {
        @Override
        String criateUrlResponse(Purchase purchase,
                UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPaypal = uriComponentsBuilder
                    .path("/response-paypal/{id}").buildAndExpand(purchase.getId())
                    .toString();

            return "paypal.com/" + purchase.getId() + "?redirectUrl=" + urlRetornoPaypal;
        }
    };

    abstract String criateUrlResponse(Purchase purchase,
            UriComponentsBuilder uriComponentsBuilder);
}
