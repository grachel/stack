package com.stack.service;

import com.stack.model.entities.User;
import com.stack.model.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;

@Service
public class UserService {

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    UserRepository userRepository;

    public User findByLogin(String username) {
        return userRepository.findByLogin(username);
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByLogin(username);
    }
}
