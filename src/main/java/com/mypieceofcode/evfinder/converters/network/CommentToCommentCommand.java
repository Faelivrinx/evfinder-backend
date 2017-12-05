package com.mypieceofcode.evfinder.converters.network;

import com.mypieceofcode.evfinder.command.event.CommentCommand;
import com.mypieceofcode.evfinder.domain.Comment;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class CommentToCommentCommand implements Converter<Comment, CommentCommand> {

    @Synchronized
    @Override
    public CommentCommand convert(Comment comment) {
        CommentCommand commentCommand = new CommentCommand();
        commentCommand.setId(comment.getCommentId());
        commentCommand.setComment(comment.getComment());
        commentCommand.setRating(comment.getRating());
        commentCommand.setAuthor(comment.getUser().getUsername());
        commentCommand.setEventId(comment.getEvent().getEventId());
        commentCommand.setUserID(comment.getUser().getUserId());
        return commentCommand;
    }
}
