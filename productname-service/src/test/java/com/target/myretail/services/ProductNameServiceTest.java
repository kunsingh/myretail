package com.target.myretail.services;

import com.target.myretail.models.Product;
import com.target.myretail.repositories.ProductNameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.util.Assert;

import java.util.Optional;

@SpringBootTest
@Profile("test")
public class ProductNameServiceTest {

    @MockBean
    private ProductNameRepository repository;

    @Autowired
    private ProductNameService productNameService;

    @Test
    public void shouldBeAbleToGetProductNameById() throws Exception{
        Mockito.when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(new Product(123456l,"The Big Lebowski (Blu-ray) (Widescreen)")));
        final String productName = productNameService.getProductNameById(123456l);
        Assert.isTrue(productName.equalsIgnoreCase("The Big Lebowski (Blu-ray) (Widescreen)"), "Return product name is not as expected");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNullProductId() {
        Exception exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> productNameService.getProductNameById(null)
        );
        Assert.isTrue(exception.getMessage().contains("Argument 'productId' may not be null"), "Wrong message!");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNegativeProductId() {
        Exception exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> productNameService.getProductNameById(-12345l)
        );
        Assert.isTrue(exception.getMessage().contains("Argument 'productId' may not be negative"), "Wrong message!");
    }


}
