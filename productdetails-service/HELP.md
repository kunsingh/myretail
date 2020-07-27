# productdetails-service

A simple Spring boot application to get details of a product by id.
    
    
    GET 
    /myretail/v1/products/{productId} : Return the product details
    
        Request URL: http://localhost:8082/myretail/v1/products/13860428
        Response Body: 
        {
          "id": 13860428,
          "name": "The Big Lebowski (Blu-ray) (Widescreen)",
          "current_price": {
            "value": 13.49,
            "currency_code": "USD"
          }
        } 
        
    PUT 
    /myretail/v1/products/{productId} : Update the product price
         
        Request URL: http://localhost:8082/myretail/v1/products/13860428
        Request Body: 
        {
          "id": 13860428,
          "name": "The Big Lebowski (Blu-ray) (Widescreen)",
          "current_price": {
           "value": 13.49,
           "currency_code": "USD"
          }
        } 
       
        Response : 202, Accepted
    
- Start mongodb locally
    ``` 
    sudo ./mongod --dbpath=../data/db --port 27017
    ```    
- To Run the application
    ```
    cd productsetails-service
    mvn spring-boot:run
    ```
  
- you can also start the ApplicationStarter.java in your editor like eclipse/intellij

- Swagger api is configured

    http://localhost:8080/myretail/swagger-ui.html

- To run unit test
    ```
    mvn test
    ```
- If any issue while running contact: kunal.s.rathor@gmail.com

