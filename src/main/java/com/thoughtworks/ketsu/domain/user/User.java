package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import java.util.*;

public class User implements Record{
    private long id;
    private String name;

    @Inject
    OrderMapper orderMapper;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("uri", routes.userUrl(getId()));
            put("name", getName());
            put("id", String.valueOf(getId()));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

    public Order placeOrder(Map info) {
        orderMapper.save(info, getId());
        return orderMapper.findById(Long.valueOf(info.get("id").toString()));
    }

    public Optional<Order> getOrderById(long id) {
        return Optional.ofNullable(orderMapper.findById(id));
    }

    public List<Order> getAllOrders() {
        return orderMapper.findAllOf(getId());
    }
}
