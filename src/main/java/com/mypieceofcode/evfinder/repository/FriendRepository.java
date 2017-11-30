package com.mypieceofcode.evfinder.repository;

import com.mypieceofcode.evfinder.domain.Friend;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, Long> {

    List<Friend> findByUser_ApiToken(String apiToken);

    @Modifying
    @Query("delete from Friend f where f.id = ?1")
    void delete(Long aLong);
}
