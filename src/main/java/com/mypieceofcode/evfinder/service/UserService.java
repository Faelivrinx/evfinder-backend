package com.mypieceofcode.evfinder.service;

import com.mypieceofcode.evfinder.command.ApiKey;
import com.mypieceofcode.evfinder.command.UserCommand;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.domain.security.Role;
import com.mypieceofcode.evfinder.domain.security.UserRole;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UserService extends CRUDService<User> {

    User findByUsername(String username);

    /**
     * Used to create new User in database with roles.
     * @param user
     * @param roles
     * @return
     */
    User createUser(User user, Set<UserRole> roles);
    User createUser(User user);
    ApiKey createUserByUserCommand(UserCommand userCommand);
    User findUserByApiKey(String apiKey);

}
