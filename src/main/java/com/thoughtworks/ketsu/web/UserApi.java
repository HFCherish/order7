package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class UserApi {
    private User user;

    public UserApi(User user) {
        this.user = user;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        return user;
    }

    @Path("orders")
    public OrdersApi toOrder() {
        return new OrdersApi(user);
    }
}
