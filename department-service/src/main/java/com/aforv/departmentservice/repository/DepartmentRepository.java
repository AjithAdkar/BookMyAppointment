package com.aforv.departmentservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<com.aforv.departmentservice.entity.Department, Long> {
}