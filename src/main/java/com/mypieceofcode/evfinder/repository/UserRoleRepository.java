package com.mypieceofcode.evfinder.repository;

import com.mypieceofcode.evfinder.domain.security.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long > {
}
