package br.com.zup.ecommerce.category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("/categories")
public class CategoryController {
    
    @PersistenceContext
    EntityManager manager;
    
    @PostMapping
    public CategoryResponse saveCategory(@Valid @RequestBody CategoryRequest request) {
        Category category = request.converter(manager);
        manager.persist(category);
        
        return new CategoryResponse(category);
    }

}
