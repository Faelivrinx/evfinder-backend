package com.mypieceofcode.evfinder.converters;

import com.mypieceofcode.evfinder.command.EventCommand;
import com.mypieceofcode.evfinder.domain.Event;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class EventToEventCommand implements Converter<Event, EventCommand> {

    @Synchronized
    @Override
    public EventCommand convert(Event event) {
        EventCommand eventCommand = new EventCommand();
        eventCommand.setName(event.getName());
        eventCommand.setAddress(event.getAddress());
        eventCommand.setDescription(event.getDescription());
        eventCommand.setDate(event.getDate());
        eventCommand.setProfile(event.getProfile());
        eventCommand.setLatitude(event.getLatitude());
        eventCommand.setLongitude(event.getLongitude());
        return eventCommand;
    }
}
