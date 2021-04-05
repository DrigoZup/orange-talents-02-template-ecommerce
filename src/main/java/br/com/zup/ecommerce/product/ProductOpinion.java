package br.com.zup.ecommerce.product;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import br.com.zup.ecommerce.opinion.Opinion;

public class ProductOpinion {

    private Set<Opinion> opinions;

    public ProductOpinion(Set<Opinion> opinions) {
        this.opinions = opinions;
    }
    
    public <T> Set<T> mapperOpinion(Function<Opinion, T> mapperFunction) {
        return this.opinions.stream().map(mapperFunction)
                .collect(Collectors.toSet());
    }

    public double avarage() {
        Set<Integer> rating = mapperOpinion(opinion -> opinion.getRating());
        OptionalDouble probableAvarage = rating.stream().mapToInt(rate -> rate).average();
        return probableAvarage.orElse(0.0);
    }   
    
    public int total() {
        return this.opinions.size();
    }
}
