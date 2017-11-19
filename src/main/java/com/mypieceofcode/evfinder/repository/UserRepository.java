package com.mypieceofcode.evfinder.repository;

import com.mypieceofcode.evfinder.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findByApiToken(String apiToken);
}
