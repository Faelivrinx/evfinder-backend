package com.mypieceofcode.evfinder.controller;

import com.mypieceofcode.evfinder.command.UserCommand;
import com.mypieceofcode.evfinder.command.UserProfileCommand;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "user/profile")
    public UserProfileCommand getProfile(@RequestHeader("Authorization")String key){
        User user = userService.findUserByApiKey(key);

        UserProfileCommand profileCommand = new UserProfileCommand();
        if (user != null){
            profileCommand.setProfile(user.getProfile());
            profileCommand.setUsername(user.getUsername());
            return profileCommand;
        }
        throw new IllegalArgumentException("User with key " + key + " not created");
    }
}
