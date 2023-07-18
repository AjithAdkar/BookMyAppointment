package com.aforv.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aforv.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}