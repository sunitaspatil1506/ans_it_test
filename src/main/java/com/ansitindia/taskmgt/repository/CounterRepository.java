package com.ansitindia.taskmgt.repository;

import com.ansitindia.taskmgt.model.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<Counter, String> {
    Counter findByCollectionName(String collectionName);
}
