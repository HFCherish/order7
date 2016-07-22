package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrderOperationTest {
    private User user;
    private Product product;

    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Before
    public void setUp() {
        user = prepareUser(userRepository);
        product = prepareProduct(productRepository);
    }

    @Test
    public void should_save_and_get_order() {
        Order order = user.placeOrder(orderJsonForTest(product.getId()));
        Optional<Order> fetched = user.getOrderById(order.getId());

        assertThat(fetched.isPresent(), is(true));
        assertThat(fetched.get().getId(), is(order.getId()));
    }

    @Test
    public void should_get_all() {
        Order order = prepareOrder(user, product);

        List<Order> fetched = user.getAllOrders();

        assertThat(fetched.size(), is(1));
        assertThat(fetched.get(0).getId(), is(order.getId()));

    }
}
