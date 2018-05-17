package com.stack.model.repo.user;

import com.stack.model.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>, UserRepositoryCustom {
    User findByLogin(String login);
}
