package com.mypieceofcode.evfinder.controller;

import com.mypieceofcode.evfinder.command.event.CommentCommand;
import com.mypieceofcode.evfinder.command.event.EventCommand;
import com.mypieceofcode.evfinder.converters.network.CommentToCommentCommand;
import com.mypieceofcode.evfinder.converters.network.EventCommandToEvent;
import com.mypieceofcode.evfinder.domain.Comment;
import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.repository.CommentRepository;
import com.mypieceofcode.evfinder.service.EventService;
import com.mypieceofcode.evfinder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Collections;

@RestController
public class CommentController {

    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    private UserService userService;
    private EventService eventService;
    private CommentRepository commentRepository;
    private EventCommandToEvent eventCommandToEvent;
    private CommentToCommentCommand commentToCommentCommand;

    public CommentController(UserService userService, EventService eventService, CommentRepository commentRepository, EventCommandToEvent eventCommandToEvent, CommentToCommentCommand commentToCommentCommand) {
        this.userService = userService;
        this.eventService = eventService;
        this.commentRepository = commentRepository;
        this.eventCommandToEvent = eventCommandToEvent;
        this.commentToCommentCommand = commentToCommentCommand;
    }

    @PostMapping("comment/add")
    public CommentCommand addComment(@RequestHeader("Authorization") String token, @RequestBody CommentCommand commentCommand) {
        LOG.info("Try to save comment");
        User user = userService.findUserByApiKey(token);
        EventCommand event = eventService.findById(commentCommand.getEventId());

        if (user == null || event == null) {
            throw new IllegalStateException("User or event can't find");
        }

        for (CommentCommand comment : event.getCommentCommands()) {
            if (comment.getUserID() == user.getUserId()){
                return new CommentCommand();
            }
        }

        Comment comment = new Comment();
        comment.setEvent(eventCommandToEvent.convert(event));
        comment.setUser(user);
        comment.setComment(commentCommand.getComment());
        comment.setRating(commentCommand.getRating());
        LOG.info("Comment save");

        return commentToCommentCommand.convert(commentRepository.save(comment));
    }
}
