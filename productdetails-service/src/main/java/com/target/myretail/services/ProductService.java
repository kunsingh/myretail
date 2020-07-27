package com.target.myretail.services;

import com.target.myretail.models.Product;
import com.target.myretail.exceptions.ProductDetailsNotFoundException;

public interface ProductService {

    /**
     * Api to get product with pricing details.
     * @param productId is of the product
     * @return Product detailed product with price.
     */
    Product getProductById(final Long productId) throws ProductDetailsNotFoundException;

    /**
     * Api to update product price info
     * @param product product to update
     */
    void updateProductPrice(final Product product) throws ProductDetailsNotFoundException;
}
