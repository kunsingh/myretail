package com.target.myretail.controllers;

import com.target.myretail.exceptions.ProductDetailsNotFoundException;
import com.target.myretail.models.Product;
import com.target.myretail.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(tags = "Api to get product with pricing information")
@RestController
@RequestMapping("/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Update a product price for given id.", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server error")
    })
    @CrossOrigin
    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProductPrice(@PathVariable("id") final Long id,
                                                   @RequestBody @Valid Product product) throws ProductDetailsNotFoundException {
        productService.updateProductPrice(product);
        return new ResponseEntity<String>("Successfully updated", HttpStatus.ACCEPTED);
    }


    @ApiOperation(value = "Return the product and price details for given productId", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") @NotNull(message="productId cannot be null") final Long productId) throws ProductDetailsNotFoundException {
        return productService.getProductById(productId);
    }
}
