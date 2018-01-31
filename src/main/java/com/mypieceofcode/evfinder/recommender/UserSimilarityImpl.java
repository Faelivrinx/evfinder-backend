package com.mypieceofcode.evfinder.recommender;

import com.mypieceofcode.evfinder.command.Coordinate;
import com.mypieceofcode.evfinder.domain.Comment;
import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.Friend;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.service.EventService;
import com.mypieceofcode.evfinder.service.FriendService;
import com.mypieceofcode.evfinder.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSimilarityImpl implements UserSimilarity {

    private EventService eventService;
    private UserService userService;
    private FriendService friendService;


    private User user;

    private UserSimilarityImpl(EventService eventService, UserService userService, FriendService friendService) {
        this.eventService = eventService;
        this.userService = userService;
        this.friendService = friendService;
    }

    @Override
    public List<User> findSimilarUsersWithThreshold(Coordinate coordinate, double similarThreshold) {
        List<User> result = new ArrayList<>();
        if (isUserSelected()) {
            List<Event> events = eventService.findLocalDomainEvents(user, coordinate);
            List<Friend> friends = friendService.findByApiToken(user.getApiToken());
            List<User> usersFriends = new ArrayList<>();
            findFriends(friends, usersFriends);

            for (User usersFriend : usersFriends) {
                double similarity = computeThreshold(user, usersFriend, events);
                if (similarity > similarThreshold) {
                    usersFriend.setSimilarity(similarity);
                    result.add(usersFriend);
                }
            }

            for (User user1 : result) {
                System.out.println("User: " + user1.getUsername() + " with similarity: " + user1.getSimilarity());
            }
            return result;
        }
        throw new IllegalArgumentException("User not set!");
    }

    private void findFriends(List<Friend> friends, List<User> usersFriends) {
        for (Friend friend : friends) {
            User user = userService.findByUsername(friend.getUsername());
            if (user != null) {
                usersFriends.add(user);
            }
        }
    }

    @Override
    public List<User> findSimilarUsers(Coordinate coordinate, int usersCount) {
        return null;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean isUserSelected() {
        return this.user != null;
    }


    private double computeThreshold(User user, User friend, List<Event> events) {
        double averageUserRating = computeAverage(user, events);
        double averageFriendRating = computeAverage(friend, events);


        double sumNumerator = 0;
        double sumUserDenominator = 0;
        double sumFriendDenominator = 0;

        for (Event event : events) {
            Comment userComment = isCommented(user, event);
            Comment friendComment = isCommented(friend, event);

            if (userComment != null) {
                sumUserDenominator += Math.pow(userComment.getRating() - averageUserRating, 2);
            }

            if (friendComment != null) {
                sumFriendDenominator += Math.pow(friendComment.getRating() - averageFriendRating, 2);
            }

            if (userComment != null && friendComment != null) {
                double x = (double) userComment.getRating() - averageUserRating;
                double y = (double) friendComment.getRating() - averageFriendRating;
                sumNumerator += (x * y);

            }
        }

        if (sumNumerator == 0) {
            return 0;
        }

        double result = sumNumerator / (Math.sqrt(sumUserDenominator) * Math.sqrt(sumFriendDenominator));
        return result;
    }

    private Comment isCommented(User user, Event event) {
        for (Comment comment : user.getComments()) {
            for (Comment comment1 : event.getComments()) {
                if (comment.getEvent().getEventId() == comment1.getEvent().getEventId())
                    return comment;
            }
        }
        return null;
    }

    private double computeAverage(User user, List<Event> events) {
        double sumRatingsUser = 0;
        double countRatingsUser = 0;

        for (Event event : events) {
            Comment userComment = isCommented(user, event);
            if (userComment != null) {
                sumRatingsUser += userComment.getRating();
                countRatingsUser++;
            }
        }
        return sumRatingsUser / countRatingsUser;
    }
}
