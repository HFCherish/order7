package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {

//
//    public static User userForTest(String id, String name, UserRole role) {
//        String password_123 = "$2a$04$DbgJbGA4dkQSzAvXvJcGBOv5kHuMBzrWfne3x3Cx4JQv4IJcxtBIW";
//        return new User(new UserId(id), name, name + "@tw.com", role, password_123);
//    }
//
//    public static User userFixture(UserRepository userRepository, UserRole role) {
//        final String id = new Integer(auto_increment_key++).toString();
//        User user = userForTest(id, "name-" + id, role);
//        userRepository.save(user);
//        return user;
//    }
//
    public static Product prepareProduct(ProductRepository productRepository) {
        return productRepository.save(productJsonForTest());
    }

    public static Map<String, Object> productJsonForTest() {
        return new HashMap<String, Object>() {{
            put("name", "Imran");
            put("description", "teachers");
            put("price", 1.1);
        }};
    }
}
