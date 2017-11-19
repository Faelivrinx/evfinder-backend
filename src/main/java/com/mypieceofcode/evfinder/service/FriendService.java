package com.mypieceofcode.evfinder.service;

import com.mypieceofcode.evfinder.domain.Friend;

import java.util.List;

public interface FriendService extends CRUDService<Friend> {

    List<Friend> findByApiToken(String apiToken);
    void deleteById(Long id);

}
