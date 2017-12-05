package com.mypieceofcode.evfinder.converters.network;

import com.mypieceofcode.evfinder.command.event.EventCommand;
import com.mypieceofcode.evfinder.domain.Event;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class EventCommandToEvent implements Converter<EventCommand, Event> {

    @Autowired
    CommentCommandToComment commentCommandToComment;

    @Synchronized
    @Override
    public Event convert(EventCommand eventCommand) {
        Event event = new Event();
        if (eventCommand.getId() != null){
            event.setEventId(eventCommand.getId());
        }
        event.setName(eventCommand.getName());
        event.setAddress(eventCommand.getAddress());
        event.setDescription(eventCommand.getDescription());
        event.setProfile(eventCommand.getProfile());
        event.setDate(eventCommand.getDate());
        event.setLatitude(eventCommand.getLatitude());
        event.setLongitude(eventCommand.getLongitude());
        if (!eventCommand.getCommentCommands().isEmpty() && eventCommand.getCommentCommands() != null){
            eventCommand.getCommentCommands()
                    .forEach(commentCommand -> event.getComments().add(commentCommandToComment.convert(commentCommand)));
        }
        return event;
    }
}
