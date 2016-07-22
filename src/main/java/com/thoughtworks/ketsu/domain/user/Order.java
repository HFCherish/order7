package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.*;

public class Order implements Record {
    @Inject
    OrderMapper orderMapper;

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
            put("total_price", getTotalPrice());

        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        List itemsInfo = new ArrayList<>();
        for (OrderItem item : orderItems) {
            itemsInfo.add(item.toJson(routes));
        }
        Map<String, Object> info = toRefJson(routes);
        info.put("order_items", itemsInfo);
        return info;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public double getTotalPrice() {
        double res = 0;
        for (OrderItem orderItem : orderItems)
            res += orderItem.getAmount() * orderItem.getQuantity();
        return res;
    }

    public Payment pay(Map payInfo) {
        orderMapper.pay(payInfo, getId());
        return orderMapper.findPayment(getId());
    }


    public Optional<Payment> getPayment() {
        return Optional.ofNullable(orderMapper.findPayment(getId()));
    }
}
