package com.mypieceofcode.evfinder.service.reposervices;

import com.mypieceofcode.evfinder.domain.Friend;
import com.mypieceofcode.evfinder.repository.FriendRepository;
import com.mypieceofcode.evfinder.service.FriendService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FriendServiceRepoImpl implements FriendService {

    private FriendRepository friendRepository;

    public FriendServiceRepoImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    public Friend save(Friend object) {
        return friendRepository.save(object);
    }

    @Override
    public void delete(Friend object) {
        System.out.println("delete friend");
        try {
            friendRepository.delete(object);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Friend> findAll() {
        Iterable<Friend> friends = friendRepository.findAll();
        List<Friend> friendList = new ArrayList<>();
        friends.forEach(friendList::add);

        if (friendList.size() > 0) {
            return friendList;
        }

        return Collections.emptyList();
    }

    @Override
    public Friend findById(Long id) {
        return friendRepository.findOne(id);
    }

    @Override
    public List<Friend> findByApiToken(String apiToken) {
        List<Friend> friends = friendRepository.findByUser_ApiToken(apiToken);
        List<Friend> friendList = new ArrayList<>();
        friendList.addAll(friends);

        if (friendList.size() > 0) {
            return friendList;
        }

        return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
        try {
            friendRepository.delete(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
