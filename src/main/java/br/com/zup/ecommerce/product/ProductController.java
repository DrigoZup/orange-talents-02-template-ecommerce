package br.com.zup.ecommerce.product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@Transactional
public class ProductController {

    
    @PersistenceContext
    EntityManager manager;
    
    @InitBinder(value = "ProductRequest")
    public void init(WebDataBinder binder) {
        binder.addValidators(new ForbidDuplicateNamesValidator());
    }

    
    @PostMapping
    public ProductResponse saveProduct(@Valid @RequestBody ProductRequest request) {
        
        Product product = request.convertToEntity(manager);
        manager.persist(product);
        
        return new ProductResponse(product);
    }

}
