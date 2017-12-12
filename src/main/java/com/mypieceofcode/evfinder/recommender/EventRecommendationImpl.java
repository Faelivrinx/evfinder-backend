package com.mypieceofcode.evfinder.recommender;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypieceofcode.evfinder.converters.recommender.ProfileConverter;
import com.mypieceofcode.evfinder.domain.Comment;
import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class EventRecommendationImpl implements EventRecommendation {

    private ProfileConverter profileConverter;

    @Autowired
    public EventRecommendationImpl(ProfileConverter profileConverter) {
        this.profileConverter = profileConverter;
    }

    @Override
    public List<Event> recommend(User user, List<Event> objectsToSort) {
        int[] userProfile = profileConverter.convertJsonStringToArray(user.getProfile());
        if (userProfile == null) {
            throw new IllegalStateException("Cannot convert user profile: " + user.getProfile());
        }

        for (Event event : objectsToSort) {
            int[] eventProfile = profileConverter.convertJsonStringToArray(event.getProfile());
            event.setCorrelation(measureCorrelation(userProfile, eventProfile));
        }

        Collections.sort(objectsToSort, (event1, event2) -> {
            if (event1.getCorrelation() == event2.getCorrelation()) {
                return 0;
            }

            return event1.getCorrelation() < event2.getCorrelation() ? 1 : -1;
        });

        return objectsToSort;
    }

    private double measureCorrelation(int[] userProfile, int[] eventProfile) {
        int sum = 0;
        int sumUser = 0;
        int sumEvent = 0;

        for (int i = 0; i < 31; i++) {
            sum += userProfile[i] * eventProfile[i];
        }

        for (int i = 0; i < 31; i++) {
            if (userProfile[i] != 0) {
                sumUser += Math.pow(userProfile[i], 2);
            }
        }

        for (int i = 0; i < 31; i++) {
            if (eventProfile[i] != 1) {
                sumEvent += Math.pow(eventProfile[i], 2);
            }
        }

        return sum / (Math.sqrt(sumUser) * Math.sqrt(sumEvent));
    }

    @Override
    public List<Event> recommendByUsers(User user, List<User> similarUsers, List<Event> localEvents) {
        if (similarUsers.size() > 0) {
            List<Event> eventsToRecommendation = new ArrayList<>();
            for (Event localEvent : localEvents) {
                if (isCommented(user, localEvent) == null) {
                    eventsToRecommendation.add(localEvent);
                }
            }
            double avUserRating = computeAverage(user, localEvents);

            for (Event localevent : eventsToRecommendation) {
                double sumDenominator = 0;
                double sumSimilariry = 0;
                for (User similarUser : similarUsers) {
                    double avFriendRating = computeAverage(similarUser, localEvents);
                    for (Comment comment : similarUser.getComments()) {
                        if (comment.getEvent().getEventId() == localevent.getEventId()) {
                            sumSimilariry += similarUser.getSimilarity();
                            sumDenominator += similarUser.getSimilarity() * (comment.getRating() - avFriendRating);
                        }
                    }
                }
                if (sumSimilariry == 0) {
                    localevent.setExpectedRating(0);
                } else {
                    double resultRating = avUserRating + (sumDenominator / sumSimilariry);
                    localevent.setExpectedRating(resultRating);
                }
            }

            Collections.sort(eventsToRecommendation, (event1, event2) -> {
                if (event1.getExpectedRating() == event2.getExpectedRating()) {
                    return 0;
                }

                return event1.getExpectedRating() < event2.getExpectedRating() ? 1 : -1;
            });

            return eventsToRecommendation;
        }

        return Collections.emptyList();
    }


    private double computeAverage(User user, List<Event> events) {
        double sumRatingsUser = 0;
        double countRatingsUser = 0;

        for (Event event : events) {
            Comment userComment = isCommented(user, event);
            if (userComment != null) {
                sumRatingsUser += userComment.getRating();
                countRatingsUser += 1;
            }
        }
        return sumRatingsUser / countRatingsUser;
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


}
