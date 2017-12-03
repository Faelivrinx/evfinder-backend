package com.mypieceofcode.evfinder.service;

import com.mypieceofcode.evfinder.command.Coordinate;
import com.mypieceofcode.evfinder.command.EventCommand;
import com.mypieceofcode.evfinder.domain.User;

import java.util.List;

public interface EventService extends CRUDService<EventCommand>{

    List<EventCommand> findLocalEvents(double latitude, double longitude);
    List<EventCommand> findLocalEventsWithRecommendation(User user, Coordinate coordinate);
}
