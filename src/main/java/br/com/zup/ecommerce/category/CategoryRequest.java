package br.com.zup.ecommerce.category;

import static br.com.zup.ecommerce.general.ConstantResponse.FIELD_CANNOT_BE_BLANK;
import static br.com.zup.ecommerce.general.ConstantResponse.ID_CANNOT_BE_NULL;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import org.springframework.util.Assert;
import br.com.zup.ecommerce.general.UniqueValue;

public class CategoryRequest {

    @NotBlank(message = FIELD_CANNOT_BE_BLANK)
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private String name;
    
    private Long idCategory;
    
    public CategoryRequest(String name, Long idCategory) {
        this.name = name;
        this.idCategory = idCategory;
    }
    
    public Category converter(EntityManager manager) {
        Category category = new Category(name);
        if(idCategory == null) {
            Category motherCategory = manager.find(Category.class, idCategory);
            Assert.notNull(motherCategory, ID_CANNOT_BE_NULL);
            
            category.setMother(motherCategory);
        }
        return category;
    }
}
