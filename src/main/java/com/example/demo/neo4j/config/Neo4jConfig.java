package com.example.demo.neo4j.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.example.demo.neo4j.repository")
@EntityScan(basePackages = "com.example.demo.neo4j.entity")
@EnableTransactionManagement
public class Neo4jConfig
{
    @Bean
    public org.neo4j.ogm.config.Configuration configuration()
    {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
                .uri("bolt://192.168.1.104:7687")
                .credentials("neo4j", "123456")
                .build();

        return configuration;
    }

    @Bean
    public SessionFactory sessionFactory()
    {
        return new SessionFactory(configuration(), "com.example.demo.neo4j.entity");
    }

    @Bean
    public Neo4jTransactionManager transactionManager()
    {
        return new Neo4jTransactionManager(sessionFactory());
    }
}
