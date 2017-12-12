package com.mypieceofcode.evfinder.service;

import com.mypieceofcode.evfinder.command.Coordinate;
import com.mypieceofcode.evfinder.command.event.EventCommand;
import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.User;

import java.util.List;

public interface EventService extends CRUDService<EventCommand>{

    List<Event> findLocalDomainEvents(User user, Coordinate coordinate);
    List<EventCommand> findLocalEvents(double latitude, double longitude);
    List<EventCommand> findLocalEventsWithRecommendation(User user, Coordinate coordinate);
    List<EventCommand> findLocalEventsUsedUserRecommendation(User user, Coordinate coordinate);
}
