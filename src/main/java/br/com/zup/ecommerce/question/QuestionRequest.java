package br.com.zup.ecommerce.question;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.user.User;
import br.com.zup.ecommerce.user.current.AuthUser;

public class QuestionRequest {

    @NotBlank
    private String title;
    
    @NotNull
    private User user;

    @NotNull
    private Long idProduct;

    public Question converterToEntity(EntityManager manager) {

        AuthUser auth = new AuthUser(user);
        Product product = manager.find(Product.class, idProduct);
        return new Question(title, auth.get(), product);
    }

}
