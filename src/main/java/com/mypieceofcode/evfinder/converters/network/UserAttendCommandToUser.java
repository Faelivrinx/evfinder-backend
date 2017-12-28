package com.mypieceofcode.evfinder.converters.network;

import com.mypieceofcode.evfinder.command.event.UserAttendCommand;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.repository.UserRepository;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserAttendCommandToUser implements Converter<UserAttendCommand, User> {

    @Autowired
    private UserRepository userRepository;

    @Synchronized
    @Override
    public User convert(UserAttendCommand userAttendCommand) {
        User user = userRepository.findOne(userAttendCommand.getId());
        user.setUserId(userAttendCommand.getId());
        user.setUsername(userAttendCommand.getName());
        return user;
    }
}
