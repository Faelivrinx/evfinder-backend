package com.mypieceofcode.evfinder.repository;

import com.mypieceofcode.evfinder.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
