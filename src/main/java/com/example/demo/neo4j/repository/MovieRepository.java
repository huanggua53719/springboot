package com.example.demo.neo4j.repository;


import com.example.demo.neo4j.entity.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie, Long>
{
    Movie findByTitle(@Param("title") String title);
}
