package com.mypieceofcode.evfinder.recommender;

import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.User;

import java.util.List;

public interface EventRecommendation extends Recommendation<Event> {
    List<Event> recommendByUsers(User user, List<User> similarUsers, List<Event> localEvents);
}
