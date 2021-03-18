package br.com.zup.ecommerce.user;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Transactional
public class UserController {

    @Autowired
    UserRepository repository;
    
    @PostMapping
    public UserResponse saveUser(@Valid @RequestBody UserResquest request) {
        User user = request.converter();
        repository.save(user);
        
        return new UserResponse(user);
    }
}
