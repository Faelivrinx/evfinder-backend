package com.mypieceofcode.evfinder.controller;

import com.mypieceofcode.evfinder.command.Coordinate;
import com.mypieceofcode.evfinder.command.EventCommand;
import com.mypieceofcode.evfinder.command.TaskResponse;
import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.recommender.EventRecommendation;
import com.mypieceofcode.evfinder.repository.EventRepository;
import com.mypieceofcode.evfinder.service.EventService;
import com.mypieceofcode.evfinder.service.UserService;
import com.mypieceofcode.evfinder.utils.TaskFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private TaskFactory taskFactory;

    @Autowired
    private EventRepository eventRepository;


    @PostMapping("events")
    public List<EventCommand> localEvents(@RequestBody Coordinate coordinate){
        List<EventCommand> localEvents = eventService.findLocalEvents(coordinate.getLatitude(), coordinate.getLongitude());

        if (localEvents != null){
            return localEvents;
        }

        return Collections.emptyList();
    }

    @PostMapping("events/recommendation")
    public List<EventCommand> recommendEvent(@RequestHeader("Authorization")String key, @RequestBody Coordinate coordinate){
        User user = userService.findUserByApiKey(key);
        if (user == null){
            throw new IllegalArgumentException("Can't find user with key: " + key);
        }

        return eventService.findLocalEventsWithRecommendation(user, coordinate);
    }

    @PostMapping("event/add")
    public TaskResponse add(@RequestBody EventCommand command){
        EventCommand event = eventService.save(command);
        if (event != null){
            return taskFactory.createTaskResponse(true, "ADD_EVENT");
        }
        return taskFactory.createTaskResponse(false, "ADD_EVENT");
    }

    @PostMapping("events/all")
    public List<Event> getEvents(){
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(events::add);


        return events;
    }
}
