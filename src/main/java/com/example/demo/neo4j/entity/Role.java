package com.example.demo.neo4j.entity;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "ACTS_IN")
public class Role
{
    @Id
    @GeneratedValue
    Long id;
    String role;
    @StartNode
    Actor actor;
    @EndNode
    Movie movie;

    public Role()
    {
    }

    public Role(String role, Actor actor, Movie movie)
    {
        this.role = role;
        this.actor = actor;
        this.movie = movie;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public Actor getActor()
    {
        return actor;
    }

    public void setActor(Actor actor)
    {
        this.actor = actor;
    }

    public Movie getMovie()
    {
        return movie;
    }

    public void setMovie(Movie movie)
    {
        this.movie = movie;
    }
}
