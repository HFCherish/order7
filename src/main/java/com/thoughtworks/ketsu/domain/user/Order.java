package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order implements Record{
    private long id;
    private long userId;
    private String name;
    private String address;
    private String phone;
    private List<OrderItem> orderItems;
    private DateTime createdAt;

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("uri", routes.orderUrl(getUserId(), getId()));
            put("name", getName());
            put("address", getAddress());
            put("phone", getPhone());
            put("created_at", getCreatedAt());

        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        List itemsInfo = new ArrayList<>();
        for(OrderItem item: orderItems) {
            itemsInfo.add(item.toJson(routes));
        }
        Map<String, Object> info = toRefJson(routes);
        info.put("order_items", itemsInfo);
        return info;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }
}
