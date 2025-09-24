package com.example.ssrf.ssrf_example.repository;

import com.example.ssrf.ssrf_example.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
}