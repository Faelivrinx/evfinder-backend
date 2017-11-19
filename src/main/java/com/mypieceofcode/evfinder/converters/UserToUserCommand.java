package com.mypieceofcode.evfinder.converters;

import com.mypieceofcode.evfinder.command.UserCommand;
import com.mypieceofcode.evfinder.domain.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserToUserCommand implements Converter<User, UserCommand> {

    @Synchronized
    @Override
    public UserCommand convert(User user) {
        UserCommand userCommand = new UserCommand();
        userCommand.setUsername(user.getUsername());
        userCommand.setEmail(user.getEmail());
        userCommand.setPassword(user.getPassword());
        userCommand.setFcm_token(user.getFcmToken());
        return userCommand;
    }
}
