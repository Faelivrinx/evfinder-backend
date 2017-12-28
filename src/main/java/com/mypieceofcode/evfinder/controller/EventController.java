package com.mypieceofcode.evfinder.controller;

import com.mypieceofcode.evfinder.command.Coordinate;
import com.mypieceofcode.evfinder.command.event.CommentCommand;
import com.mypieceofcode.evfinder.command.event.EventCommand;
import com.mypieceofcode.evfinder.command.TaskResponse;
import com.mypieceofcode.evfinder.command.event.UserAttendCommand;
import com.mypieceofcode.evfinder.converters.network.CommentCommandToComment;
import com.mypieceofcode.evfinder.converters.network.EventCommandToEvent;
import com.mypieceofcode.evfinder.converters.network.UserToUserAttendCommand;
import com.mypieceofcode.evfinder.converters.network.UserToUserCommand;
import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.repository.CommentRepository;
import com.mypieceofcode.evfinder.repository.EventRepository;
import com.mypieceofcode.evfinder.service.EventService;
import com.mypieceofcode.evfinder.service.UserService;
import com.mypieceofcode.evfinder.utils.TaskFactory;
import com.mypieceofcode.evfinder.utils.TaskFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserToUserAttendCommand userToUserCommand;

    @Autowired
    private CommentCommandToComment commentCommandToComment;


    @PostMapping("events")
    public List<EventCommand> localEvents(@RequestBody Coordinate coordinate) {
        List<EventCommand> localEvents = eventService.findLocalEvents(coordinate.getLatitude(), coordinate.getLongitude());

        if (localEvents != null) {
            return localEvents;
        }

        return Collections.emptyList();
    }

    @GetMapping("event/{id}/delete")
    public TaskResponse deleteEvent(@PathVariable("id")Long id){
        EventCommand event = eventService.findById(id);
        event.getUsers().clear();

        for (CommentCommand commentCommand : event.getCommentCommands()) {
            commentRepository.delete(commentCommand.getId());
        }
        event.getCommentCommands().clear();

        EventCommand clearedEvent = eventService.save(event);
        if (clearedEvent != null)
            eventService.delete(event);
        else {
            return taskFactory.createTaskResponse(false, "Event Deleted");
        }
        return taskFactory.createTaskResponse(true, "Event Deleted");
    }

    @PostMapping("events/recommendation")
    public List<EventCommand> recommendEvent(@RequestHeader("Authorization") String key, @RequestBody Coordinate coordinate) {
        User user = userService.findUserByApiKey(key);
        if (user == null) {
            throw new IllegalArgumentException("Can't find user with key: " + key);
        }

        if (coordinate.getRecommendationType() == 0) {
            return eventService.findLocalEvents(coordinate.getLatitude(), coordinate.getLongitude());
        } else if (coordinate.getRecommendationType() == 1) {
            return eventService.findLocalEventsWithRecommendation(user, coordinate);
        } else if (coordinate.getRecommendationType() == 2) {
            return eventService.findLocalEventsUsedUserRecommendation(user, coordinate);
        } else if (coordinate.getRecommendationType() == 3){
            return eventService.findByFriends(user, coordinate);
        }

        return eventService.findLocalEventsWithRecommendation(user, coordinate);
    }

    @PostMapping("event/add")
    public TaskResponse add(@RequestBody EventCommand command) {
        EventCommand event = eventService.save(command);
        if (event != null) {
            return taskFactory.createTaskResponse(true, "ADD_EVENT");
        }
        return taskFactory.createTaskResponse(false, "ADD_EVENT");
    }

    @PostMapping("events/all")
    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(events::add);


        return events;
    }

    @PostMapping("events/recommendation/test")
    public List<EventCommand> getRecommentedEvents(@RequestHeader("Authorization") String key, @RequestBody Coordinate coordinate) {
        User user = userService.findUserByApiKey(key);
        if (user == null) {
            throw new IllegalArgumentException("Can't find user with key " + key);
        }

        return eventService.findLocalEventsUsedUserRecommendation(user, coordinate);
    }

    @GetMapping("event/{id}/attend")
    public EventCommand attendAtEvent(@RequestHeader("Authorization") String token, @PathVariable("id") Long id){
        User user = userService.findUserByApiKey(token);
        EventCommand event = eventService.findById(id);

        for (UserAttendCommand userAttendCommand : event.getUsers()) {
            if (userAttendCommand.getId() == user.getUserId()){
                return new EventCommand();
            }
        }

        if (user == null || event == null){
            throw new IllegalStateException("Can't find user or event");
        }

        event.getUsers().add(userToUserCommand.convert(user));
        return eventService.save(event);
    }
}
