package com.coderhack.codehackapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.coderhack.codehackapi.entities.User;

public interface UserRepository extends MongoRepository<User,String>{
    
}
