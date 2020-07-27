package com.target.myretail.repositories;

import com.target.myretail.models.Price;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceRepository extends MongoRepository<Price, Long> {
}
