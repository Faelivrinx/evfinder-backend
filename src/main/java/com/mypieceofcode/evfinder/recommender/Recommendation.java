package com.mypieceofcode.evfinder.recommender;

import com.mypieceofcode.evfinder.domain.User;

import java.util.List;

public interface Recommendation<T> {

    List<T> recommend(User user, List<T> objectsToSort);
}

