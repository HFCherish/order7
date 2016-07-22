package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;

import java.util.Map;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Product save(Map prodInfo) {
        return null;
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(new Product());
    }
}
