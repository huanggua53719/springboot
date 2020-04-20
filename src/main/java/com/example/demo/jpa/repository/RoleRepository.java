package com.example.demo.jpa.repository;

import com.example.demo.jpa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>
{
}
