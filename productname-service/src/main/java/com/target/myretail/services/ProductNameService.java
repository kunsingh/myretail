package com.target.myretail.services;

import com.target.myretail.exceptions.ProductNameNotFoundException;

public interface ProductNameService {

    /**
     * Api to get product name.
     * @param productId is of the product
     * @return Product name.
     */
    String getProductNameById(final Long productId) throws ProductNameNotFoundException;
}
