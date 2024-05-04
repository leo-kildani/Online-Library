package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.demo.entity.User;

public interface UserRepositoryI extends Repository<User, String> {

    // save or update a user to db
    void save(User user);

    // find a string match to provided username
    User findByUsername(String username);

    // find similar/substring match to provided username
    List<User> findLikeUsername(String username);

    // find all users
    List<User> findAll();

    // delete user by username
    void deleteByUsername(String username);

    // find a string match to provided email
    User findByEmail(String name, String server, String domain);

    // find a similar/substring match to provided name
    List<User> findLikeName(String name);
}
