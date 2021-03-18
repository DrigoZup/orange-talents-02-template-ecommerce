package br.com.zup.ecommerce.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToOne
    private Category motherCategory;
    
    @Deprecated
    public Category() {
    }
    
    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public Category getMother() {
        return motherCategory;
    }
    
    public void setMother(Category motherCategory) {
        this.motherCategory = motherCategory;
    }

}
