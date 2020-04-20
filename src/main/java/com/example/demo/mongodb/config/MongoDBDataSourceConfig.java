package com.example.demo.mongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.demo.mongodb.repository")
@PropertySource("classpath:mongo.properties")
public class MongoDBDataSourceConfig extends AbstractMongoConfiguration
{

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName()
    {
        return env.getRequiredProperty("mongo.name");
    }


    @Override
    public MongoClient mongoClient()
    {
        ServerAddress serverAddress = new ServerAddress(env.getRequiredProperty("mongo.host"));
        List<MongoCredential> credentials = new ArrayList<>();
        MongoCredential credential = MongoCredential.createCredential("test", "admin", "123456".toCharArray());
        credentials.add(credential);
        return new MongoClient(serverAddress,credentials);
    }
}
