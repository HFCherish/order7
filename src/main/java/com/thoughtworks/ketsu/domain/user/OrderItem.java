package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

public class OrderItem implements Record{
    private int quantity;
    private long productId;
    private double amount;

    public int getQuantity() {
        return quantity;
    }

    public long getProductId() {
        return productId;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap() {{
            put("product_id", getProductId());
            put("quantity", getQuantity());
            put("amount", getAmount());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
