package com.target.myretail.controllers;

import com.target.myretail.exceptions.ProductNameNotFoundException;
import com.target.myretail.services.ProductNameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Api(tags = "Name service for products")
@RestController
@RequestMapping("/v1")
public class ProductNameController {


    @Autowired
    private ProductNameService productNameService;


    @ApiOperation(value = "Return the product name for given productId", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/productname/{id}")
    public String getProductName(@PathVariable("id") @NotNull(message="productId cannot be null") final Long productId) throws ProductNameNotFoundException {
        return productNameService.getProductNameById(productId);
    }
}
