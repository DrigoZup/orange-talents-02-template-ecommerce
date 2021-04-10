package br.com.zup.ecommerce.product.details;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/details")
@Transactional
public class ProductDetailsController {

    
    @PersistenceContext
    private EntityManager manager;

    @GetMapping
    public ProductDetails getMethodName() {
        return new ProductDetails(manager);
    }

}
