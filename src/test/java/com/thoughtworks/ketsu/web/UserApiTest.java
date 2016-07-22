package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

import java.util.HashMap;

import static com.thoughtworks.ketsu.support.TestHelper.INVALID_USER_NAME;
import static com.thoughtworks.ketsu.support.TestHelper.USER_NAME;
import static com.thoughtworks.ketsu.support.TestHelper.userJsonForTest;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UserApiTest extends ApiSupport {
    private String usersBaseUrl = "/users";

    @Test
    public void should_register() {
        Response response = post(usersBaseUrl, userJsonForTest(USER_NAME));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(usersBaseUrl));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));

    }

    @Test
    public void should_400_when_register_given_invalid_param() {
        Response response = post(usersBaseUrl, new HashMap());

        assertThat(response.getStatus(), is(400));

        response = post(usersBaseUrl, userJsonForTest(INVALID_USER_NAME));

        assertThat(response.getStatus(), is(400));

    }
}
