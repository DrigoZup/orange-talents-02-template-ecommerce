package br.com.zup.ecommerce.product;

import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.zup.ecommerce.product.images.ImageRequest;
import br.com.zup.ecommerce.user.User;

@RestController
@RequestMapping("/products")
@Transactional
public class ProductController {

    
    @PersistenceContext
    EntityManager manager;
    
    @Autowired
    Uploader fakeUpload;
    
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
    
    @PutMapping(value = "/{id}/images")
    public ProductResponse addImages(@PathVariable Long id,@Valid ImageRequest request) {
        
        Product product = manager.find(Product.class, id);
        
        User current = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (!product.getOwner().equals(current)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        
        Set<String> links = fakeUpload.send(request.getImages());
        product.relateImages(links);
        
        manager.merge(product);
        
        return new ProductResponse(product);
        
    }

}
