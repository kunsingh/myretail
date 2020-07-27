package com.target.myretail.services.Impl;

import com.target.myretail.exceptions.ProductNameNotFoundException;
import com.target.myretail.models.Product;
import com.target.myretail.repositories.ProductNameRepository;
import com.target.myretail.services.ProductNameService;
import com.target.myretail.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductNameServiceImpl implements ProductNameService {

    @Autowired
    private ProductNameRepository productRepository;

    /**
     * Api to get product name.
     * @param productId is of the product
     * @return Product name.
     */
    public String getProductNameById(final Long productId) throws ProductNameNotFoundException {
        Assert.requireNonNull(productId, "productId");
        Assert.requireNonNegative(productId, "productId");

        final Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            return productOptional.get().getName();
        }
        throw new ProductNameNotFoundException("No Product exist with id: "+productId);
    }
}
