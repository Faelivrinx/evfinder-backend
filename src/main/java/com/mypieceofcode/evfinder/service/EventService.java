package com.mypieceofcode.evfinder.service;

import com.mypieceofcode.evfinder.command.EventCommand;

import java.util.List;

public interface EventService extends CRUDService<EventCommand>{

    List<EventCommand> findLocalEvents(double latitude, double longitude);
}
