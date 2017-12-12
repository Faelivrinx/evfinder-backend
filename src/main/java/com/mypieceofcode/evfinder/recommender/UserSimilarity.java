package com.mypieceofcode.evfinder.recommender;

import com.mypieceofcode.evfinder.command.Coordinate;
import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.User;

import java.util.List;

public interface UserSimilarity  {

    void setUser(User user);
    boolean isUserSelected();
    List<User> findSimilarUsersWithThreshold(Coordinate coordinate, double similarThreshold);
    List<User> findSimilarUsers(Coordinate coordinate, int usersCount);

}
