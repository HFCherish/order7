package com.thoughtworks.ketsu.web.validators;

import com.thoughtworks.ketsu.domain.product.ProductRepository;

import javax.inject.Inject;
import java.util.*;

public class OrderValidator extends FieldNotNullValidator {
    @Inject
    ProductRepository productRepository;

    @Override
    public Map<String, List> getNullFields(List<String> toValidates, Map<String, Object> info) {
        Map<String, List> errors = super.getNullFields(toValidates, info);

        List nullFields = errors.get("items");
        if(info.get("order_items") != null) {
            List itemsInfo = (List)info.get("order_items");
            for (Object o : itemsInfo) {
                Map item = (Map) o;
                nullFields.addAll((List) super.getNullFields(Arrays.asList("product_id", "quantity"), item).get("items"));
                if (!productRepository.findById(Long.valueOf(item.get("product_id").toString())).isPresent()) {
                    nullFields.add(new HashMap() {{
                        put("field", "product_id");
                        put("message", "product_id is invalid");
                    }});
                }

            }
        }
        return errors;
    }

}
