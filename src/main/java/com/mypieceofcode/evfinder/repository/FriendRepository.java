package com.mypieceofcode.evfinder.repository;

import com.mypieceofcode.evfinder.domain.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, Long> {

    List<Friend> findByUser_ApiToken(String apiToken);
    void deleteById(Long id);
}
