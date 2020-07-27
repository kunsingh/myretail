package com.target.myretail.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document("Price")
public class Price {

    @Id
    @JsonIgnore
    @NotNull(message="productId cannot be null")
    private Long productId;

    @NotNull(message="Value cannot be null")
    private Double value;

    @JsonProperty("currency_code")
    @NotNull(message="currencyCode cannot be null")
    private CurrencyCode currencyCode;

    public Price(Long productId, Double value, CurrencyCode currencyCode) {
        this.productId = productId;
        this.value = value;
        this.currencyCode = currencyCode;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }
}
