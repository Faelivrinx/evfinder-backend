package com.mypieceofcode.evfinder.converters.network;

import com.mypieceofcode.evfinder.command.EventCommand;
import com.mypieceofcode.evfinder.domain.Event;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class EventCommandToEvent implements Converter<EventCommand, Event> {

    @Synchronized
    @Override
    public Event convert(EventCommand eventCommand) {
        Event event = new Event();
        event.setName(eventCommand.getName());
        event.setAddress(eventCommand.getAddress());
        event.setDescription(eventCommand.getDescription());
        event.setProfile(eventCommand.getProfile());
        event.setDate(eventCommand.getDate());
        event.setLatitude(eventCommand.getLatitude());
        event.setLongitude(eventCommand.getLongitude());
        return event;
    }
}
