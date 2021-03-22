package br.com.zup.ecommerce.product.images;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;
import br.com.zup.ecommerce.product.Product;

@Entity
public class ImageProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @Valid
    private Product product;
    @URL
    @NotBlank
    private String link;
    
    @Deprecated
    public ImageProduct() {

    }

    public ImageProduct(@Valid Product product, @URL @NotBlank String link) {
        this.product = product;
        this.link = link;
    }


    public Product getProduct() {
        return product;
    }

    public String getLink() {
        return link;
    }
}
