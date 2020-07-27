package com.target.myretail.services.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.target.myretail.models.Price;
import com.target.myretail.models.Product;
import com.target.myretail.exceptions.ProductDetailsNotFoundException;
import com.target.myretail.repositories.PriceRepository;
import com.target.myretail.services.ProductService;
import com.target.myretail.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private PriceRepository priceRepository;

    @Value("${productname.url}")
    private String productNameServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProductById(Long productId) throws ProductDetailsNotFoundException {
        Assert.requireNonNull(productId, "productId");
        Assert.requireNonNegative(productId, "productId");

        Product product = new Product();
        product.setId(productId);
        product.setName(getProductName(productId));
        product.setCurrentPrice(getPriceInfo(productId));

        return product;
    }

    @Override
    public void updateProductPrice(Product product) throws ProductDetailsNotFoundException {
        Assert.requireNonNull(product, "product");

        final Price price = getPriceInfo(product.getId());
        price.setValue(product.getCurrentPrice().getValue());
        price.setCurrencyCode(product.getCurrentPrice().getCurrencyCode());
        priceRepository.save(price);
    }

    private Price getPriceInfo(final long productId) throws ProductDetailsNotFoundException {
        final Optional<Price> priceOptional = priceRepository.findById(productId);
        if(priceOptional.isPresent()){
            return priceOptional.get();
        }
        LOGGER.error("Price details are not available for product id: {}", productId);
        throw new ProductDetailsNotFoundException("Price details are not available for product id: "+ productId);
    }

    @HystrixCommand(fallbackMethod = "defaultHandling")
    public String getProductName(long productId) {
        return restTemplate.getForObject(productNameServiceUrl+"/productname/{productId}",
                        String.class, productId);
    }

    private String defaultHandling(long productId) throws ProductDetailsNotFoundException {
        LOGGER.error("No Product name returned from external service for product id: {}", productId);
        throw new ProductDetailsNotFoundException("No Product name returned from external service for product id: "+ productId);
    }
}
