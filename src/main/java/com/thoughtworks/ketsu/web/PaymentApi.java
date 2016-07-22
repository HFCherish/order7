package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.web.validators.FieldNotNullValidator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PaymentApi {
    private Order order;

    public PaymentApi(Order order) {
        this.order = order;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response pay(Map payInfo) {
        Map<String, List> nullFields = new FieldNotNullValidator().getNullFields(Arrays.asList("pay_type", "amount"), payInfo);
        if(nullFields.get("items").size() > 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nullFields).build();
        }

        order.pay(payInfo);
        return Response.created(URI.create("")).build();
    }
}
