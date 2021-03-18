package br.com.zup.ecommerce.user;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
    Optional<User> findByLogin(String login);

}
