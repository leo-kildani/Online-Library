package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserRole;
import java.util.List;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query(value = "SELECT * FROM user_roles WHERE username = :username", nativeQuery = true)
    List<UserRole> findByUsername(String username);
}
