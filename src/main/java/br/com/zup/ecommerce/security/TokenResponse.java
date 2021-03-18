package br.com.zup.ecommerce.security;

public class TokenResponse {

    private String token;
    
    public TokenResponse(String token) {
        this.token = token;
    }
    
    public String getToken() {
        return token;
    }
}
