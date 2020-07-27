package com.target.myretail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myretail.controllers.ProductController;
import com.target.myretail.exceptions.ProductDetailsNotFoundException;
import com.target.myretail.models.CurrencyCode;
import com.target.myretail.models.Price;
import com.target.myretail.models.Product;
import com.target.myretail.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ComponentScan(basePackages = "com.target.myretail.controllers")
public class ProductControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void validCallToGetProductShouldReturnStatus200AndResponseAsProduct() throws Exception{
        Mockito.when(productService.getProductById(Mockito.any(Long.class))).thenReturn(getProduct());
        mockMvc.perform(get("/v1/products/{id}", 123456l)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":123456,\"name\":\"The Big Lebowski (Blu-ray) (Widescreen)\",\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}"));

    }

//    @Test
    public void validCallToUpdateProductShouldReturnStatus200AndResponseAsSuccess() throws Exception{
        mockMvc.perform(put("/v1/products/{id}", 12345)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(getProduct())))
                .andExpect(status().isAccepted());

    }

    @Test
    public void callToGetProductNameWithNonExistingProductIdShouldReturnStatus404NotFound() throws Exception{
        Mockito.when(productService.getProductById(Mockito.any(Long.class))).thenThrow(ProductDetailsNotFoundException.class);
        mockMvc.perform(get("/v1//products/{id}", "12313113")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Product details not found"));

    }

    @Test
    public void callToGetProductNameWithIllegalArgumentShouldReturnStatus400Badrequest() throws Exception{
        Mockito.when(productService.getProductById(Mockito.any(Long.class))).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(get("/v1//products/{id}", "-321313132")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Illegal Argument"));

    }

    @Test
    public void callToGetProductNameWithNonLongProductIdShouldReturnStatus500ServerError() throws Exception{
        Mockito.when(productService.getProductById(Mockito.any(Long.class))).thenThrow(RuntimeException.class);
        mockMvc.perform(get("/v1//products/{id}", "sdfsffsfs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal server error"));

    }

    private Product getProduct(){

        Product product = new Product();
        product.setId(123456l);
        product.setName("The Big Lebowski (Blu-ray) (Widescreen)");
        product.setCurrentPrice(new Price(13860428l, 13.49, CurrencyCode.USD));

        return product;
    }

}