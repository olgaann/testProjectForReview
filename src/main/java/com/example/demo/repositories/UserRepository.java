package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUsername(String username);
}
