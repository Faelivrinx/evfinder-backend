package com.mypieceofcode.evfinder.controller;

import com.mypieceofcode.evfinder.command.Coordinate;
import com.mypieceofcode.evfinder.command.EventCommand;
import com.mypieceofcode.evfinder.command.TaskResponse;
import com.mypieceofcode.evfinder.service.EventService;
import com.mypieceofcode.evfinder.utils.TaskFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private TaskFactory taskFactory;

    @PostMapping("events")
    public List<EventCommand> localEvents(@RequestBody Coordinate coordinate){
        List<EventCommand> localEvents = eventService.findLocalEvents(coordinate.getLatitude(), coordinate.getLongitude());

        if (localEvents != null){
            return localEvents;
        }

        return Collections.emptyList();
    }

    @PostMapping("event/add")
    public TaskResponse add(@RequestBody EventCommand command){
        EventCommand event = eventService.save(command);
        if (event != null){
            return taskFactory.createTaskResponse(true, "ADD_EVENT");
        }
        return taskFactory.createTaskResponse(false, "ADD_EVENT");
    }
}