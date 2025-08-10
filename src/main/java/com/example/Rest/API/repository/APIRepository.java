package com.example.Rest.API.repository;

import com.example.Rest.API.entity.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface APIRepository extends MongoRepository<Entity,String> {
}
