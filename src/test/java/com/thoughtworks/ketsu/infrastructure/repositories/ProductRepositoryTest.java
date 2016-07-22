package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.prepareProduct;
import static com.thoughtworks.ketsu.support.TestHelper.productJsonForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductRepositoryTest {
    @Inject
    ProductRepository productRepository;

    @Test
    public void should_save_and_get_product() {

        Product product = productRepository.save(productJsonForTest());
        Optional<Product> fetched = productRepository.findById(product.getId());

        assertThat(fetched.isPresent(), is(true));
        assertThat(fetched.get().getId(), is(product.getId()));
    }

    @Test
    public void should_get_all() {
        Product product = prepareProduct(productRepository);

        List<Product> fetched = productRepository.findAll();

        assertThat(fetched.size(), is(1));

    }
}
