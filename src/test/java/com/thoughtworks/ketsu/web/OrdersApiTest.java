package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.OrderItem;
import com.thoughtworks.ketsu.domain.user.User;
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
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrdersApiTest extends ApiSupport {
    private String orderBaseUrl;
    private User user;
    private Product product;

    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        user = prepareUser(userRepository);
        product = prepareProduct(productRepository);
        orderBaseUrl = "/users/" + user.getId() + "/orders";
    }

    @Test
    public void should_create_order() {
        Response response = post(orderBaseUrl, orderJsonForTest(product.getId()));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(orderBaseUrl));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));
    }

    @Test
    public void should_400_when_create_order_given_incomplete_param() {
        Response response = post(orderBaseUrl, new HashMap());

        assertThat(response.getStatus(), is(400));
        List errors = (List)response.readEntity(Map.class).get("items");
        Map nameEmpty = (Map) errors.get(0);
        assertThat(nameEmpty.get("field"), is("name"));
        assertThat(nameEmpty.get("message").toString(), containsString("name can not be empty"));
    }

    @Test
    public void should_400_when_create_order_given_invalid_order_items_info() {
        Response response = post(orderBaseUrl, orderJsonForTest(product.getId()+1));

        assertThat(response.getStatus(), is(400));

    }

    @Test
    public void should_get_order() {
        Order order = prepareOrder(user, product);
        String getOneUrl = orderBaseUrl + "/" + order.getId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(200));
        Map orderInfo = response.readEntity(Map.class);
        assertThat(orderInfo.get("uri").toString(), containsString(getOneUrl));
        assertThat(orderInfo.get("name"), is(order.getName()));
        assertThat(orderInfo.get("address"), is(order.getAddress()));
        assertThat(orderInfo.get("phone"), is(order.getPhone()));
        assertThat((double)orderInfo.get("total_price"), is(order.getTotalPrice()));
        assertThat(new DateTime(orderInfo.get("created_at")), is(order.getCreatedAt()));

        List items = (List)orderInfo.get("order_items");
        assertThat(items.size(), is(1));
        Map itemInfo = (Map)items.get(0);
        OrderItem item = order.getOrderItems().get(0);
        assertThat(Long.valueOf(itemInfo.get("product_id").toString()), is(item.getProductId()));
        assertThat((double)itemInfo.get("amount"), is(product.getPrice()));
        assertThat(itemInfo.get("quantity"), is(item.getQuantity()));
    }

    @Test
    public void should_404_when_get_order_given_not_exists() {
        Order order = prepareOrder(user, product);
        String getOneUrl = orderBaseUrl + "/1" + order.getId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(404));

    }
}
