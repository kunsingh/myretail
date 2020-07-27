# productname-service

A simple Spring boot application to get name of a product by id.
    
    
    GET 
    /myretail/v1/productname/{productId} : Return the product name
    
        Request URL: http://localhost:8082/myretail/v1/productname/13860428
        Response Body: "The Big Lebowski (Blu-ray) (Widescreen)"       
    
- Start mongodb locally
    ``` 
    sudo ./mongod --dbpath=../data/db --port 27017
    ```
- To Run the application
    ```
    cd productname-service
    mvn spring-boot:run
    ```
  
- you can also start the ApplicationStarter.java in your editor like eclipse/intellij

- Swagger api is configured

    http://localhost:8082/myretail/swagger-ui.html

- To run unit test
    ```
    mvn test
    ```
- If any issue while running contact: kunal.s.rathor@gmail.com

