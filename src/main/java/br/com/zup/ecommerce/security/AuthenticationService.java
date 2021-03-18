package br.com.zup.ecommerce.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.zup.ecommerce.user.User;
import br.com.zup.ecommerce.user.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByLogin(username);
        if (user.isEmpty()) {
            
            throw new UsernameNotFoundException("Unavalible Datas!");
        }
        return user.get();
    }
}
