package com.target.myretail.controller;

import com.target.myretail.controllers.ProductNameController;
import com.target.myretail.exceptions.ProductNameNotFoundException;
import com.target.myretail.services.ProductNameService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductNameController.class)
@ComponentScan(basePackages = "com.target.myretail.controllers")
public class ProductNameControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductNameService productNameService;

    @Test
    public void validCallToGetProductNameShouldReturnStatus200AndResponseAsSuccess() throws Exception{
        Mockito.when(productNameService.getProductNameById(Mockito.any(Long.class))).thenReturn("The Big Lebowski (Blu-ray) (Widescreen)");
        mockMvc.perform(get("/v1//productname/{id}", "13860428")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("The Big Lebowski (Blu-ray) (Widescreen)"));

    }

    @Test
    public void callToGetProductNameWithNonExistingProductIdShouldReturnStatus404NotFound() throws Exception{
        Mockito.when(productNameService.getProductNameById(Mockito.any(Long.class))).thenThrow(ProductNameNotFoundException.class);
        mockMvc.perform(get("/v1//productname/{id}", "12313113")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Product name not found"));

    }

    @Test
    public void callToGetProductNameWithIllegalArgumentShouldReturnStatus400Badrequest() throws Exception{
        Mockito.when(productNameService.getProductNameById(Mockito.any(Long.class))).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(get("/v1//productname/{id}", "-321313132")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Illegal Argument"));

    }

    @Test
    public void callToGetProductNameWithNonLongProductIdShouldReturnStatus500ServerError() throws Exception{
        Mockito.when(productNameService.getProductNameById(Mockito.any(Long.class))).thenThrow(RuntimeException.class);
        mockMvc.perform(get("/v1//productname/{id}", "sdfsffsfs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal server error"));

    }


}