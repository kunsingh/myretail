package com.target.myretail.services;

import com.target.myretail.models.CurrencyCode;
import com.target.myretail.models.Price;
import com.target.myretail.models.Product;
import com.target.myretail.repositories.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@SpringBootTest
@Profile("test")
public class ProductServiceTest {

    @MockBean
    private PriceRepository repository;
    
    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private ProductService productService;
    
    

    @Test
    public void shouldBeAbleToGetProductById() throws Exception{
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class),Mockito.anyLong())).thenReturn("The Big Lebowski (Blu-ray) (Widescreen)");
        Mockito.when(repository.findById(Mockito.any(Long.class))).thenReturn(getPrice());
        final Product product = productService.getProductById(123456l);
        Assert.isTrue(product.getName().equalsIgnoreCase("The Big Lebowski (Blu-ray) (Widescreen)"), "Return product name is not as expected");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNullProductId() {
        Exception exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> productService.getProductById(null)
        );
        Assert.isTrue(exception.getMessage().contains("Argument 'productId' may not be null"), "Wrong message!");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNegativeProductId() {
        Exception exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> productService.getProductById(-12345l)
        );
        Assert.isTrue(exception.getMessage().contains("Argument 'productId' may not be negative"), "Wrong message!");
    }

    @Test
    public void shouldBeAbleToUpdateProduct() throws Exception{
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class),Mockito.anyLong())).thenReturn("The Big Lebowski (Blu-ray) (Widescreen)");
        Mockito.when(repository.findById(Mockito.any(Long.class))).thenReturn(getPrice());
        productService.updateProductPrice(getProduct());
        final Product product = productService.getProductById(123456l);
        Assert.isTrue(product.getName().equalsIgnoreCase("The Big Lebowski (Blu-ray) (Widescreen)"), "Return product name is not as expected");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNullProduct() {
        Exception exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> productService.updateProductPrice(null)
        );
        Assert.isTrue(exception.getMessage().contains("Argument 'product' may not be null"), "Wrong message!");
    }

    private Optional<Price> getPrice() {
        return Optional.of(new Price(13860428l, 13.49, CurrencyCode.USD));
    }

    private Product getProduct(){
        Product product = new Product();
        product.setId(123456l);
        product.setName("The Big Lebowski (Blu-ray) (Widescreen)");
        product.setCurrentPrice(new Price(13860428l, 13.49, CurrencyCode.USD));

        return product;
    }
}
