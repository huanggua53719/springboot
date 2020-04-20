package com.example.demo.mongodb.repository;

import com.example.demo.mongodb.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentRepository extends MongoRepository<UserDocument, String>
{
    UserDocument findByUserName(String userName);
}
