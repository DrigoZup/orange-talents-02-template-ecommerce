package br.com.zup.ecommerce.product.tests;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import br.com.zup.ecommerce.category.Category;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.product.attribute.AttributeRequest;
import br.com.zup.ecommerce.user.User;

public class ProductTests {

    @DisplayName("Check if the product has at least three features")
    @ParameterizedTest
    @MethodSource("generateTest1")
    void test1(Collection<AttributeRequest> attributes) throws Exception{
        Category category = new Category("Category test");
        User owner = new User("user@email.com", "123456");
        new Product("drigo", 23, "blablalba", 23.3, category, owner, attributes);
    }

    static Stream<Arguments> generateTest1() {
        return Stream.of(
                Arguments.of(
                            List.of(new AttributeRequest("key1", "value1"),
                                    new AttributeRequest("key2", "value2"),
                                    new AttributeRequest("key3", "value3"))),
                Arguments.of(
                            List.of(new AttributeRequest("key1", "value1"),
                                    new AttributeRequest("key2", "value2"),
                                    new AttributeRequest("key3", "value3"),
                                    new AttributeRequest("key4", "value4"))));
    }
    
    @DisplayName("Check if a product has three distinct features")
    @ParameterizedTest
    @MethodSource("generateTest2")
    void test2(Collection<AttributeRequest> attributes) throws Exception{
        Category category = new Category("Category test");
        User owner = new User("user@email.com", "123456");
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Product("drigo", 23, "blablalba", 23.3, category, owner, attributes);
        });
    }

    static Stream<Arguments> generateTest2() {
        return Stream.of(
                Arguments.of(
                            List.of(new AttributeRequest("key1", "value1"),
                                    new AttributeRequest("key2", "value2"))),
                Arguments.of(
                            List.of(new AttributeRequest("key3", "value3"))));
    }
    
}
