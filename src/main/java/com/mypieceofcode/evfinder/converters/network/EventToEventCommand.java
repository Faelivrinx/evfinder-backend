package com.mypieceofcode.evfinder.converters.network;

import com.mypieceofcode.evfinder.command.event.EventCommand;
import com.mypieceofcode.evfinder.domain.Comment;
import com.mypieceofcode.evfinder.domain.Event;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class EventToEventCommand implements Converter<Event, EventCommand> {

    @Autowired
    CommentToCommentCommand commentToCommentCommand;

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
        return eventCommand;
    }
}
