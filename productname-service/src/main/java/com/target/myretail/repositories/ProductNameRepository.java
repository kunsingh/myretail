package com.target.myretail.repositories;

import com.target.myretail.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductNameRepository extends MongoRepository<Product, Long> {
}
