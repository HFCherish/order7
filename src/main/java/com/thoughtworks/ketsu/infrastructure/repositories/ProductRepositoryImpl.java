package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    @Inject
    ProductMapper productMapper;

    @Override
    public Product save(Map prodInfo) {
        productMapper.save(prodInfo);
        return productMapper.findById(Long.valueOf(prodInfo.get("id").toString()));
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(productMapper.findById(id));
    }

    @Override
    public List<Product> findAll() {
        return Arrays.asList(new Product());
    }
}
