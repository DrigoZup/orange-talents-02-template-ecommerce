package br.com.zup.ecommerce.product.details;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import javax.persistence.EntityManager;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.product.ProductOpinion;

public class ProductDetails {

    private String descreiption;
    private String name;
    private Double price;
    
    private Set<ProductAttributeDetails> attributes;
    private Set<String> linksImages;
    private SortedSet<String> questions;
    private Set<Map<String,String>> opinions;
    private double averageGrade;
    private int total;
    private Long idProduct;

    public ProductDetails(EntityManager manager) {
        
        Product product = manager.find(Product.class, idProduct);
        
        this.descreiption = product.getDescription();
        this.name = product.getName();
        this.price = product.getPrice();

        this.attributes = product
                .mapperAttributes(ProductAttributeDetails::new);

        this.linksImages = product.mapperImages(image -> image.getLink());

        this.questions = product.mapperQuestions(question -> question.getTitle());
        
        
        ProductOpinion opinions = product.getOpinions();      
        
        this.opinions = opinions.mapperOpinion(opinion -> {
            return Map.of("titulo",opinion.getTitle(),"descricao",opinion.getDescription());
        });
        

        this.averageGrade = opinions.avarage();
        this.total = opinions.total();
    }

    public String getDescreiption() {
        return descreiption;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Set<ProductAttributeDetails> getAttributes() {
        return attributes;
    }

    public Set<String> getLinksImages() {
        return linksImages;
    }

    public SortedSet<String> getQuestions() {
        return questions;
    }

    public Set<Map<String, String>> getOpinions() {
        return opinions;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public int getTotal() {
        return total;
    }
    
}
