package com.mypieceofcode.evfinder.recommender;

import com.mypieceofcode.evfinder.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserSimilarityBuilderImpl implements UserSimilarityBuilder {

    private UserSimilarity userSimilarity;

    public UserSimilarityBuilderImpl(UserSimilarity userSimilarity) {
        this.userSimilarity = userSimilarity;
    }

    @Override
    public UserSimilarity build(User user) {
        userSimilarity.setUser(user);
        return userSimilarity;
    }
}
