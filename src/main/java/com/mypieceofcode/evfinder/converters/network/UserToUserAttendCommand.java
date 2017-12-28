package com.mypieceofcode.evfinder.converters.network;

import com.mypieceofcode.evfinder.command.event.UserAttendCommand;
import com.mypieceofcode.evfinder.domain.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserToUserAttendCommand implements Converter<User, UserAttendCommand> {

    @Synchronized
    @Override
    public UserAttendCommand convert(User user) {
        UserAttendCommand userAttendCommand = new UserAttendCommand();
        userAttendCommand.setId(user.getUserId());
        userAttendCommand.setName(user.getUsername());
        return userAttendCommand;
    }
}
