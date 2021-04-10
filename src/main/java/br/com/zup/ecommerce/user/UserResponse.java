package br.com.zup.ecommerce.user;

import java.time.LocalDate;

public class UserResponse {

    private String login;
    
    private LocalDate signInMoment;
    
    public UserResponse(User user) {
        login = user.getLogin();
        signInMoment = user.getCreateAct();
    }

    public String getLogin() {
        return login;
    }

    public LocalDate getSignInMoment() {
        return signInMoment;
    }
    
}
