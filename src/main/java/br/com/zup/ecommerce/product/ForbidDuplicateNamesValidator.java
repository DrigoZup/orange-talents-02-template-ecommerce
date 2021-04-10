package br.com.zup.ecommerce.product;

import java.util.Set;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ForbidDuplicateNamesValidator implements Validator {

    @Override
    public boolean supports(Class<?> arg0) {
        return ProductRequest.class.isAssignableFrom(arg0);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }
        
        ProductRequest request = (ProductRequest) target;
        Set<String> equalNames = request.foundDuplicatedAttributes();
        if (equalNames.isEmpty()) {
            errors.rejectValue("attributes", null, "Attributes duplicated, please rewrite");;
        }
        
    }

}
