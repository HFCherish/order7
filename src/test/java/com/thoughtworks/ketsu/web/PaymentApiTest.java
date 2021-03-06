package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.Payment;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class PaymentApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    private Order order;
    private String paymentBaseUrl;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        order = prepareOrder(prepareUser(userRepository), prepareProduct(productRepository));
        paymentBaseUrl = "/users/" + order.getUserId() + "/orders/" + order.getId() + "/payment";
    }

    @Test
    public void should_pay() {
        Response response = post(paymentBaseUrl, paymentJsonForTest());

        assertThat(response.getStatus(), is(201));

    }

    @Test
    public void should_400_when_input_invalid() {
        Response response = post(paymentBaseUrl, new HashMap());
        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_get_payment() {
        Payment payment = order.pay(paymentJsonForTest());

        Response response = get(paymentBaseUrl);

        assertThat(response.getStatus(), is(200));
        Map info = response.readEntity(Map.class);
        assertThat(info.get("pay_type"), is(payment.getType().name()));
        assertThat(info.get("amount"), is(payment.getAmount()));
        assertThat(info.get("order_uri").toString(), containsString("/users/" + order.getUserId() + "/orders/" + order.getId()));
        assertThat(info.get("uri").toString(), containsString(paymentBaseUrl));
        assertThat(new DateTime(info.get("created_at")), is(payment.getCreatedAt()));

    }

    @Test
    public void should_404_when_not_pay() {
        Response response = get(paymentBaseUrl);

        assertThat(response.getStatus(), is(404));

    }
}
