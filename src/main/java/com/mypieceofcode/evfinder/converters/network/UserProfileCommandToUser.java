package com.mypieceofcode.evfinder.converters.network;

import com.mypieceofcode.evfinder.command.UserProfileCommand;
import com.mypieceofcode.evfinder.domain.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;

// TODO: 07.12.2017 check this later
public class UserProfileCommandToUser implements Converter<UserProfileCommand, User> {
    @Synchronized
    @Override
    public User convert(UserProfileCommand userProfileCommand) {
        User user = new User();
        return null;
    }
}
