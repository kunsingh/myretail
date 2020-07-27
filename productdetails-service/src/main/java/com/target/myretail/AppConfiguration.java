package com.target.myretail;

import com.target.myretail.models.CurrencyCode;
import com.target.myretail.models.Price;
import com.target.myretail.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
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
    private PriceRepository priceRepository;

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.target.myretail"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointInfo());
    }


    private ApiInfo apiEndPointInfo() {
        return new ApiInfoBuilder().title("myretail-service")
                .description("A simple rest service to get product details")
                .version("1.0.0")
                .build();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public boolean addTestData(){
        priceRepository.saveAll(getPrices());
        return true;
    }

    private List<Price> getPrices() {
        List<Price> prices = new ArrayList<>();

        prices.add(new Price(13860428l, 13.49, CurrencyCode.USD));
        prices.add(new Price(15117729l,103.49, CurrencyCode.USD));
        prices.add(new Price(16483589l,213.49, CurrencyCode.BAHT));
        prices.add(new Price(16696652l,134.49, CurrencyCode.EURO));
        prices.add(new Price(16752456l,20000.48, CurrencyCode.INR));
        prices.add(new Price(15643793l,23.49, CurrencyCode.USD));
        return prices;
    }
}
