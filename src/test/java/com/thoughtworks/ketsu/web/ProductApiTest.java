package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.productJsonForTest;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductApiTest extends ApiSupport {
    private String productBaseUrl = "/products";

    @Test
    public void should_create_product() {
        Response response = post(productBaseUrl, productJsonForTest());

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(productBaseUrl));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));
    }

    @Test
    public void should_400_when_creat_given_incomplet_params() {
        Response response = post(productBaseUrl, new HashMap());

        assertThat(response.getStatus(), is(400));
        Map map = response.readEntity(Map.class);
        assertThat(map.get("items"), is(notNullValue()));
        List errors = (List) map.get("items");
        assertThat(errors.size(), is(3));
        Map nameError = (Map)errors.get(0);
        assertThat(nameError.get("field"), is("name"));
        assertThat(nameError.get("message").toString(), containsString("name can not be empty"));

    }
}
