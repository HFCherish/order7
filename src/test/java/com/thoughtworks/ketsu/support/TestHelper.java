package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestHelper {

    public static final String USER_NAME = "Imran";
    public static final String INVALID_USER_NAME = "DFU09';'";

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

    public static Map<String, Object> paymentJsonForTest() {
        return new HashMap<String, Object>() {{
            put("pay_type", "CASH");
            put("amount", 68970);
        }};
    }

    public static Map<String, Object> orderJsonForTest(long id) {
        return new HashMap<String, Object>() {{
            put("name", "Petrina");
            put("address", "beijing");
            put("phone", "68978");
            put("order_items", Arrays.asList(new HashMap(){{
                put("product_id", id);
                put("quantity", 2);
            }}));
        }};
    }

    public static Order prepareOrder(User user, Product product) {
        return user.placeOrder(orderJsonForTest(product.getId()));
    }

    public static Map<String, Object> userJsonForTest(String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
        }};
    }

    public static User prepareUser(UserRepository userRepository) {
        return userRepository.save(userJsonForTest(USER_NAME));
    }
}
