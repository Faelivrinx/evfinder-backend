package com.mypieceofcode.evfinder.converters.network;

import com.mypieceofcode.evfinder.command.event.EventCommand;
import com.mypieceofcode.evfinder.domain.Comment;
import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.User;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventToEventCommand implements Converter<Event, EventCommand> {

    @Autowired
    CommentToCommentCommand commentToCommentCommand;

    @Autowired
    UserToUserAttendCommand userToUserAttendCommand;

    @Synchronized
    @Override
    public EventCommand convert(Event event) {
        EventCommand eventCommand = new EventCommand();
        if (event.getEventId() != null) {
            eventCommand.setId(event.getEventId());
        }
        eventCommand.setName(event.getName());
        eventCommand.setAddress(event.getAddress());
        eventCommand.setDescription(event.getDescription());
        eventCommand.setDate(event.getDate());
        eventCommand.setProfile(event.getProfile());
        eventCommand.setLatitude(event.getLatitude());
        eventCommand.setLongitude(event.getLongitude());
        if (!event.getComments().isEmpty() && event.getComments() != null) {
            event.getComments()
                    .forEach(comment -> eventCommand.getCommentCommands().add(commentToCommentCommand.convert(comment)));
        }
        if (!event.getUsers().isEmpty() && event.getUsers() != null){
            List<User> users = event.getUsers();
            event.getUsers()
                    .forEach(user -> eventCommand.getUsers().add(userToUserAttendCommand.convert(user)));
        }
        return eventCommand;
    }
}
