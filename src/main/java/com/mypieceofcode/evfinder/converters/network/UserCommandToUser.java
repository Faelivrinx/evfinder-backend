package com.mypieceofcode.evfinder.converters.network;

import com.mypieceofcode.evfinder.command.UserCommand;
import com.mypieceofcode.evfinder.domain.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserCommandToUser implements Converter<UserCommand, User> {

    @Synchronized
    @Override
    public User convert(UserCommand userCommand) {
        User user = new User();
        user.setUsername(userCommand.getUsername());
        user.setPassword(userCommand.getPassword());
        user.setEmail(userCommand.getEmail());
        user.setFcmToken(userCommand.getFcm_token());
        return user;
    }
}
