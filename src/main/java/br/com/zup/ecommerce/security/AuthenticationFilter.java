package br.com.zup.ecommerce.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import br.com.zup.ecommerce.user.User;
import br.com.zup.ecommerce.user.UserRepository;
import br.com.zup.ecommerce.user.current.AuthUser;

public class AuthenticationFilter extends OncePerRequestFilter{
    
    private TokenService tokenService;
    private UserRepository repository;
    
    public AuthenticationFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = recoverToken(request);
        boolean valido = tokenService.isValidToken(token);
        if (valido) {
            authUser(token);
        }
        
        filterChain.doFilter(request, response);
    }
    
    private void authUser(String token) {
        Long idUser = tokenService.getIdUser(token);
        User user = repository.findById(idUser).get();
        AuthUser current = new AuthUser(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, current.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
