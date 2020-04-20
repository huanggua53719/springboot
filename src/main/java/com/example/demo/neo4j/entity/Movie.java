package com.example.demo.neo4j.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Movie
{

    @Id
    @GeneratedValue
    Long id;
    String title;
    String year;
    String tagline;
    @Relationship(type = "ACTS_IN" , direction = Relationship.INCOMING)
    List<Role> roles = new ArrayList<>();

    public Movie()
    {
    }

    public Role addRole(Actor actor, String name)
    {
        Role role = new Role(name, actor, this);
        this.roles.add(role);

        return role;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getTagline()
    {
        return tagline;
    }

    public void setTagline(String tagline)
    {
        this.tagline = tagline;
    }

    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }
}
