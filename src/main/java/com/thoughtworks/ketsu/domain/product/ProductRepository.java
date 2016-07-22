package com.thoughtworks.ketsu.domain.product;

import java.util.Map;
import java.util.Optional;

public interface ProductRepository {
    Product save(Map prodInfo);

    Optional<Product> findById(long id);
}
