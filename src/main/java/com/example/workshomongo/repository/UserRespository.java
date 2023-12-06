package com.example.workshomongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.workshomongo.domain.User;

@Repository
public interface UserRespository extends MongoRepository<User, String>{
    
}
