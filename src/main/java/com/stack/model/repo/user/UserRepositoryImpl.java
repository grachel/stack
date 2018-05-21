package com.stack.model.repo.user;

import com.stack.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        String username = "admin";// SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByLogin(username);
    }
}
