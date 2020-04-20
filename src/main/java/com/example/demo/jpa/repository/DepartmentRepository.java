package com.example.demo.jpa.repository;

import com.example.demo.jpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long>
{
}
