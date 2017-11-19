package com.mypieceofcode.evfinder.controller;

import com.mypieceofcode.evfinder.command.ApiKey;
import com.mypieceofcode.evfinder.command.UserCommand;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.service.UserService;
import com.mypieceofcode.evfinder.utils.PushNotificationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserService userService;


//    @GetMapping("/test/{token}")
//    public String test(@PathVariable("token") String token) {
//        List<String> tokens = new ArrayList<>();
//        tokens.add(token);
//       PushNotificationHelper.sendPushNotification(tokens);
//
//       return "";
//    }

    @PostMapping("/login")
    public ApiKey login(@RequestParam String username, @RequestParam("password") String password, @RequestParam("fcm_token") String token) {
        ApiKey apiKey = new ApiKey();
        User user = userService.findByUsername(username);
        if (user == null) {
            return apiKey;
        }
        if(encoder.matches(password, user.getPassword())){
            LOG.info("User logged " + user.getUsername());
            user.setFcmToken(token);
            userService.save(user);
            apiKey.setValue(user.getApiToken());
            return apiKey;
        }
        return apiKey;
    }

    @PostMapping(value = "/register", headers = "content-type=application/json", produces = "application/json")
    public ApiKey register(@RequestBody UserCommand userCommand) {
        if (userService.findByUsername(userCommand.getUsername()) == null) {
            LOG.info("User created " + userCommand.getUsername());
            return userService.createUserByUserCommand(userCommand);
        } else {
            return null;
        }
    }

//    @GetMapping("/user/{id}")
//    public User getUser(@PathVariable("id") Long id) {
//        User user = userService.findById(id);
//        return user;
//    }


}
