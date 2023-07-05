package com.codebyshatru.productservice.repository;

import com.codebyshatru.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
