package br.com.zup.ecommerce.security;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
;

@RestController
@RequestMapping("/auth")
public class LoginController {
    
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponse> auth(@RequestBody @Valid LoginRequest request) {
        UsernamePasswordAuthenticationToken loginData = request.converter();
        
        try {
            Authentication auth = authManager.authenticate(loginData);
            String token = tokenService.generateToken(auth);
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
