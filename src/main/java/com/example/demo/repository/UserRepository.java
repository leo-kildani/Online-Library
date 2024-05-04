package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class UserRepository implements UserRepositoryI {

    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(User user) {
        if (entityManager.find(User.class, user.getUsername()) != null) {
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.find(User.class, username);
    }

    @Override
    public List<User> findLikeUsername(String username) {
        String queryString = "SELECT * FROM users WHERE username LIKE :usernamePattern";
        Query query = entityManager.createNativeQuery(queryString, User.class);
        query.setParameter("usernamePattern", "%" + username + "%");
        return (List<User>) query.getResultList();
    }

    @Override
    public List<User> findAll() {
        String queryString = "SELECT * FROM users";
        Query query = entityManager.createNativeQuery(queryString, User.class);
        return (List<User>) query.getResultList();
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        User userToDelete = findByUsername(username);
        entityManager.remove(userToDelete);
    }

    @Override
    public User findByEmail(String name, String server, String domain) {
        String queryString = "SELECT * FROM users WHERE email = :emailPattern";
        Query query = entityManager.createNativeQuery(queryString, User.class);
        query.setParameter("emailPattern", name + "@" + server + "." + domain);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> findLikeName(String name) {
        String queryString = "SELECT DISTINCT * FROM users WHERE first_name = :name OR last_name = :name";
        Query query = entityManager.createNativeQuery(queryString, User.class);
        query.setParameter("name", name);
        return (List<User>) query.getResultList();
    }
}
