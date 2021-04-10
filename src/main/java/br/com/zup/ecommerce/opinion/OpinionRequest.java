package br.com.zup.ecommerce.opinion;

import static br.com.zup.ecommerce.general.ConstantResponse.FIELD_CANNOT_BE_BLANK;
import static br.com.zup.ecommerce.general.ConstantResponse.UNAVALIBLE_DATA;
import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.user.User;
import br.com.zup.ecommerce.user.current.AuthUser;

public class OpinionRequest {

    @Max(value = 5, message = UNAVALIBLE_DATA)
    @Min(value = 1, message = UNAVALIBLE_DATA)
    private Integer rating;
    
    @NotBlank(message = FIELD_CANNOT_BE_BLANK)
    private String title;
    
    @NotBlank(message = FIELD_CANNOT_BE_BLANK)
    @Size(max = 500, message = UNAVALIBLE_DATA)
    private String description;

    private User user;
    
    private Long idProduct;
    
    public Opinion converterToEntity(EntityManager manager) {
        AuthUser consumer = new AuthUser(user);
        Product product = manager.find(Product.class, idProduct);
        return new Opinion(rating, title, description, consumer.get(),product);
    }
}
