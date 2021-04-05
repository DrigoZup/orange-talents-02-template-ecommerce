package br.com.zup.ecommerce.opinion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/opinions")
@Transactional
public class OpinionController {


    @PersistenceContext
    EntityManager manager;

    @PostMapping
    public OpinionResponse saveOpinion(@Valid @RequestBody OpinionRequest request) {

        Opinion opinion = request.converterToEntity(manager);
        manager.persist(opinion);
        
        return new OpinionResponse(opinion);

    }

}
