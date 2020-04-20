package com.example.demo;

import com.example.demo.neo4j.DemoApplicationNeo4j;
import com.example.demo.neo4j.entity.Actor;
import com.example.demo.neo4j.entity.Movie;
import com.example.demo.neo4j.entity.Role;
import com.example.demo.neo4j.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplicationNeo4j.class})
public class Neo4jTest
{
    private static Logger logger = LoggerFactory.getLogger(Neo4jTest.class);

    @Autowired
    MovieRepository movieRepository;

    @Before
    public void initData()
    {
        movieRepository.deleteAll();
        Movie movie1 = new Movie();
        movie1.setTitle("movie1");
        movie1.setYear("2020-04-01");

        Movie movie2 = new Movie();
        movie2.setTitle("movie2");
        movie2.setYear("2020-04-02");

        Movie movie3 = new Movie();
        movie3.setTitle("movie3");
        movie3.setYear("2020-04-03");


        Actor actor1 = new Actor();
        actor1.setName("actor1");

        Actor actor2 = new Actor();
        actor2.setName("actor2");

        Actor actor3 = new Actor();
        actor3.setName("actor3");

        movie1.addRole(actor1,"a");
        movie1.addRole(actor2,"b");
        movie1.addRole(actor3,"c");

        movieRepository.save(movie1);
        Assert.assertNotNull(movie1.getId());

        movie2.addRole(actor1,"a");
        movie2.addRole(actor2,"b");
        movie2.addRole(actor3,"c");

        movieRepository.save(movie2);
        Assert.assertNotNull(movie2.getId());

        movie3.addRole(actor1,"a");
        movie3.addRole(actor2,"b");
        movie3.addRole(actor3,"c");

        movieRepository.save(movie3);
        Assert.assertNotNull(movie3.getId());

    }

    @Test
    public void get()
    {
        Movie movie1 = movieRepository.findByTitle("movie1");
        Assert.assertNotNull(movie1);
        logger.info("===movie=== movie:{}, {}" , movie1.getTitle() ,movie1.getYear());
        for(Role role : movie1.getRoles())
        {
            logger.info(" ========= actor:{} , role:{} ", role.getActor().getName(), role.getRole());
        }
    }
}
