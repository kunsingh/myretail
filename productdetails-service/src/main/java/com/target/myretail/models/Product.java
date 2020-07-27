package com.target.myretail.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Product {

    @NotNull(message="product id cannot be null")
    private Long id;

    @NotNull(message="Name cannot be null")
    private String name;

    @JsonProperty("current_price")
    @NotNull(message="currentPrice cannot be null")
    private Price currentPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Price currentPrice) {
        this.currentPrice = currentPrice;
    }
}
