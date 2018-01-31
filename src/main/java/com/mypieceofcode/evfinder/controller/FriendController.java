package com.mypieceofcode.evfinder.controller;

import com.mypieceofcode.evfinder.command.TaskResponse;
import com.mypieceofcode.evfinder.domain.Friend;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.network.FriendsDelete;
import com.mypieceofcode.evfinder.service.FriendService;
import com.mypieceofcode.evfinder.service.UserService;
import com.mypieceofcode.evfinder.utils.PushNotificationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class FriendController {

    public static final Logger LOG = LoggerFactory.getLogger(FriendController.class);

    @Autowired
    FriendService friendService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/friends")
    public List<Friend> all(@RequestHeader("Authorization") String token) {
        List<Friend> byApiToken = friendService.findByApiToken(token);

        LOG.info("Getting friends size: " + byApiToken.size());

        if (!byApiToken.isEmpty()) {
            return byApiToken;

        }
        return Collections.emptyList();
    }

    @PostMapping("/friends/request")
    public TaskResponse requestAdd(@RequestHeader("Authorization") String token, @RequestParam("username")String username){
        User byUsername = userService.findByUsername(username);
        User user = userService.findUserByApiKey(token);

        if (null == byUsername){
            return new TaskResponse("NULL", "error");
        }
        LOG.info("Sending request from user " + byUsername.getUsername() + " to: " + user.getUsername());

        return PushNotificationHelper.sendPushNotification(byUsername.getFcmToken(), user.getUsername());
    }

    @PostMapping("/friends/add")
    public TaskResponse addFriend(@RequestHeader("Authorization") String token, @RequestParam("username")String username){


        //admin
        User currentUser = userService.findUserByApiKey(token);

        //mobilny
        User myFriend = userService.findByUsername(username);


        //username mobilny
        Friend friend = new Friend();
        friend.setName(username);
        friend.setUsername(username);
        friend.setUser(currentUser);
        Friend save = friendService.save(friend);

        Friend friend1 = new Friend();
        friend1.setName(currentUser.getUsername());
        friend1.setUsername(currentUser.getUsername());
        friend1.setUser(myFriend);
        Friend save1 = friendService.save(friend1);


        LOG.info("Friends added " + friend.getUsername() + " --> "+ friend.getUser().getUsername());

        if (save != null || save1 != null){
            return new TaskResponse("SUBMIT_FRIEND", "success");
        }
        return new TaskResponse("SUBMIT_FRIEND", "failed");
    }

    @PostMapping(value = "/friends/delete", headers = "content-type=application/json", produces = "application/json")
    public TaskResponse deleteFriends(@RequestHeader("Authorization") String token, @RequestBody FriendsDelete friendsDelete){
        User user = userService.findUserByApiKey(token);
        TaskResponse taskResponse = new TaskResponse("DELETE_FRIENDS", "FAILED");

        LOG.info("Friends to delete  "+ friendsDelete.getUsernames().size());


        for (Friend friend : user.getFriends()) {
            for (String friendDel : friendsDelete.getUsernames()) {
                if (friend.getUsername().equals(friendDel)){
                    friendService.deleteById(friend.getId());
                    taskResponse.setSucces();
                }
            }
        }

        for (String friendUsername : friendsDelete.getUsernames()) {
            User friend = userService.findByUsername(friendUsername);
            for (Friend friend1 : friend.getFriends()) {
                if (friend1.getUsername().equals(user.getUsername())){
                    friendService.deleteById(friend1.getId());
                    taskResponse.setSucces();
                }
            }
        }

        return taskResponse;
    }

}
