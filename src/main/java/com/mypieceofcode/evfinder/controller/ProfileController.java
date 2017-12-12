package com.mypieceofcode.evfinder.controller;

import com.mypieceofcode.evfinder.command.TaskResponse;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.recommender.UserSimilarity;
import com.mypieceofcode.evfinder.recommender.UserSimilarityImpl;
import com.mypieceofcode.evfinder.service.UserService;
import com.mypieceofcode.evfinder.utils.TaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    public static final Logger LOG = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    UserService userService;

    @Autowired
    private TaskFactory taskFactory;


    @PostMapping(value = "/profile/update")
    public TaskResponse saveProfile(@RequestHeader("Authorization")String key, @RequestParam("profile") String profile){
        User user = userService.findUserByApiKey(key);
        if (user != null){
            user.setProfile(profile);
            userService.save(user);
            return taskFactory.createTaskResponse(true, "Profile Update");
        }

        return taskFactory.createTaskResponse(false, "Profile Update");
    }

}


