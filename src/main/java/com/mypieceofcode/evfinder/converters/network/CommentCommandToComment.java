package com.mypieceofcode.evfinder.converters.network;

import com.mypieceofcode.evfinder.command.event.CommentCommand;
import com.mypieceofcode.evfinder.domain.Comment;
import com.mypieceofcode.evfinder.repository.EventRepository;
import com.mypieceofcode.evfinder.repository.UserRepository;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class CommentCommandToComment implements Converter<CommentCommand, Comment> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @Synchronized
    @Override
    public Comment convert(CommentCommand commentCommand) {
        Comment comment = new Comment();
        comment.setComment(commentCommand.getComment());
        comment.setRating(commentCommand.getRating());
        comment.setEvent(eventRepository.findOne(commentCommand.getId()));
        comment.setUser(userRepository.findOne(commentCommand.getUserID()));
        return comment;
    }
}
