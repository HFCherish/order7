package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class Payment implements Record{
    private long orderId;
    private double amount;
    private PayType type;

    @Inject
    OrderMapper orderMapper;
    private DateTime createdAt;

    public long getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public PayType getType() {
        return type;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        Order order = orderMapper.findById(getOrderId());
        return new HashMap() {{
            put("order_uri", routes.orderUrl(order.getUserId(), order.getId()));
            put("uri", routes.paymentUrl(order.getUserId(), order.getId()));
            put("pay_type", getType());
            put("amount", getAmount());
            put("created_at", getCreatedAt());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }
}
