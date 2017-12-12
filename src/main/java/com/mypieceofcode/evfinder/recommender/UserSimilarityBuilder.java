package com.mypieceofcode.evfinder.recommender;

import com.mypieceofcode.evfinder.domain.User;

public interface UserSimilarityBuilder {

    UserSimilarity build(User user);

}
