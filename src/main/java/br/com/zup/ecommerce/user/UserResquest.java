package br.com.zup.ecommerce.user;

import static br.com.zup.ecommerce.general.ConstantResponse.UNAVALIBLE_EMAIL;
import static br.com.zup.ecommerce.general.ConstantResponse.FIELD_CANNOT_BE_BLANK;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.zup.ecommerce.general.UniqueValue;

public class UserResquest {

    
    @Email(message = UNAVALIBLE_EMAIL)
    @UniqueValue(domainClass = User.class, fieldName = "login", message = UNAVALIBLE_EMAIL)
    @NotBlank(message = FIELD_CANNOT_BE_BLANK)
    private String login;

    @Size(min = 6)
    @NotBlank(message = FIELD_CANNOT_BE_BLANK)
    private String password;

    public User converter() {
        return new User(login,password);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
