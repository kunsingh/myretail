package com.target.myretail;

import com.target.myretail.models.Product;
import com.target.myretail.repositories.ProductNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfiguration {

    @Autowired
    ProductNameRepository repository;


    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.target.myretail"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointInfo());
    }

    private ApiInfo apiEndPointInfo() {
        return new ApiInfoBuilder().title("myretail-service")
                .description("A simple rest service to get product names")
                .version("1.0.0")
                .build();
    }

    @Bean
    public boolean insertTestData(){
        repository.saveAll(getProducts());
        return true;
    }

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(13860428l,"The Big Lebowski (Blu-ray) (Widescreen)"));
        products.add(new Product(15117729l,"Iphone 11"));
        products.add(new Product(16483589l,"Samsung Galaxy"));
        products.add(new Product(16696652l,"Motog R"));
        products.add(new Product(16752456l,"Lenovo thinkpad"));
        products.add(new Product(15643793l,"MacBook Pro"));
        return products;
    }
}
