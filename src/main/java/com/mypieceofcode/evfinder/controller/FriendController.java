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

        if (null == byUsername){
            return new TaskResponse("NULL", "error");
        }
        LOG.info("Sending request from user " + byUsername.getUsername() + " to: " + username);

        return PushNotificationHelper.sendPushNotification(byUsername.getFcmToken(), username);
    }

    @PostMapping("/friends/add")
    public TaskResponse addFriend(@RequestHeader("Authorization") String token, @RequestParam("username")String username){

        User currentUser = userService.findUserByApiKey(token);

        Friend friend = new Friend();
        friend.setName(username);
        friend.setUsername(username);
        friend.setUser(currentUser);
        Friend save = friendService.save(friend);

        LOG.info("Friends added " + friend.getUsername() + " --> "+ friend.getUser().getUsername());

        if (save != null){
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
//                    friendService.delete(friend);
                    taskResponse.setSucces();
                }
            }
        }

        return taskResponse;
    }

}
