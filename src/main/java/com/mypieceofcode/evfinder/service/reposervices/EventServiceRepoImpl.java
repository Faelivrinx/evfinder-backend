package com.mypieceofcode.evfinder.service.reposervices;

import com.mypieceofcode.evfinder.command.Coordinate;
import com.mypieceofcode.evfinder.command.event.EventCommand;
import com.mypieceofcode.evfinder.converters.network.EventCommandToEvent;
import com.mypieceofcode.evfinder.converters.network.EventToEventCommand;
import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.Friend;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.recommender.EventRecommendation;
import com.mypieceofcode.evfinder.recommender.UserSimilarity;
import com.mypieceofcode.evfinder.recommender.UserSimilarityBuilder;
import com.mypieceofcode.evfinder.repository.EventRepository;
import com.mypieceofcode.evfinder.repository.FriendRepository;
import com.mypieceofcode.evfinder.repository.UserRepository;
import com.mypieceofcode.evfinder.service.EventService;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventServiceRepoImpl implements EventService {

    private static final Logger LOG = LoggerFactory.getLogger(EventServiceRepoImpl.class);

    @Autowired
    private EventToEventCommand eventToEventCommand;
    @Autowired
    private EventCommandToEvent eventCommandToEvent;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventRecommendation eventRecommendation;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserSimilarityBuilder builder;


    @Transactional
    @Override
    public EventCommand save(EventCommand object) {
        Event event = eventRepository.save(eventCommandToEvent.convert(object));
        if (event != null) {
            LOG.debug("Event " + event.getName() + " was saved.");
            return eventToEventCommand.convert(event);
        }
        LOG.debug("Event cannot be saved");
        return null;
    }

    @Override
    public void delete(EventCommand object) {
        eventRepository.delete(eventCommandToEvent.convert(object));
    }

    @Override
    public List<EventCommand> findAll() {
        return null;
    }

    @Override
    public EventCommand findById(Long id) {
        Event event = eventRepository.findOne(id);
        return eventToEventCommand.convert(event);
    }

    @Override
    public List<Event> findLocalDomainEvents(User user, Coordinate coordinate) {
        LOG.warn("Getting events...");
        List<Event> events = new ArrayList<>();
        Iterable<Event> allEvents = eventRepository.findAll();
        Observable.fromIterable(allEvents)
                .filter(event -> distFrom(coordinate.getLatitude(), coordinate.getLongitude(), event.getLatitude(), event.getLongitude()) < 10000)
                .doOnNext(events::add)
                .subscribe(event -> {
                }, throwable -> {
                }, () -> {
                });

        if (!events.isEmpty()) {
            return events;
        }
        return null;
    }

    @Override
    public List<EventCommand> findLocalEvents(double latitude, double longitude) {
        LOG.warn("Getting events...");
        List<Event> events = new ArrayList<>();
        List<EventCommand> eventCommands = new ArrayList<>();
        Iterable<Event> allEvents = eventRepository.findAll();
        Observable.fromIterable(allEvents)
                .filter(event -> distFrom(latitude, longitude, event.getLatitude(), event.getLongitude()) < 10000)
                .doOnNext(events::add)
                .subscribe(event -> {
                }, throwable -> {
                }, () -> {
                });

        for (Event event : events) {
            eventCommands.add(eventToEventCommand.convert(event));
        }

        if (!eventCommands.isEmpty()) {
            return eventCommands;
        }
        return null;
    }

    @Transactional
    @Override
    public List<EventCommand> findLocalEventsWithRecommendation(User user, Coordinate coordinate) {
        LOG.warn("Getting events...");
        List<Event> events = new ArrayList<>();
        List<EventCommand> eventCommands = new ArrayList<>();
        Iterable<Event> allEvents = eventRepository.findAll();
        Observable.fromIterable(allEvents)
                .filter(event -> distFrom(coordinate.getLatitude(), coordinate.getLongitude(), event.getLatitude(), event.getLongitude()) < 10000)
                .doOnNext(events::add)
                .subscribe(event -> {
                }, throwable -> {
                }, () -> {
                });

        List<Event> recommend = eventRecommendation.recommend(user, events);

        for (Event event : recommend) {
            long time = new Date().getTime();
            if (event.getDate() > time) {
                LOG.info(event.getName() + " with correlation: " + event.getCorrelation());
                eventCommands.add(eventToEventCommand.convert(event));
            }
        }

        return eventCommands;
    }

//
//    @Transactional
//    public List<EventCommand> getLocalEvents(Coordinate coordinate) {
//        LOG.warn("Getting events...");
//        List<Event> events = new ArrayList<>();
//        List<EventCommand> eventCommands = new ArrayList<>();
//        Iterable<Event> allEvents = eventRepository.findAll();
//        Observable.fromIterable(allEvents)
//                .filter(event -> distFrom(coordinate.getLatitude(), coordinate.getLatitude(), event.getLatitude(), event.getLongitude()) < 10000)
//                .doOnNext(events::add)
//                .subscribe(event -> {
//                }, throwable -> {
//                }, () -> {
//                });
//
//        for (Event event : events) {
//            eventCommands.add(eventToEventCommand.convert(event));
//        }
//
//        if (!eventCommands.isEmpty()) {
//            return eventCommands;
//        }
//        return null;
//    }

    @Transactional
    @Override
    public List<EventCommand> findLocalEventsUsedUserRecommendation(User user, Coordinate coordinate) {
        LOG.warn("Getting events...");
        // TODO: 11.12.2017 del coordinate
        List<Event> events = new ArrayList<>();
        List<EventCommand> eventCommands = new ArrayList<>();
        Iterable<Event> allEvents = eventRepository.findAll();
        Observable.fromIterable(allEvents)
                .filter(event -> distFrom(coordinate.getLatitude(), coordinate.getLongitude(), event.getLatitude(), event.getLongitude()) < 10000)
                .doOnNext(events::add)
                .subscribe(event -> {
                }, throwable -> {
                }, () -> {
                });

        UserSimilarity build = builder.build(user);
        List<Event> eventsRec = eventRecommendation.recommendByUsers(user, build.findSimilarUsersWithThreshold(coordinate, 0.4), events);
        if (eventsRec.size() > 0) {
            for (Event event : eventsRec) {
                if (event.getDate() > new Date().getTime() && event.getExpectedRating() > 0) {
                    eventCommands.add(eventToEventCommand.convert(event));
                }
            }

            return eventCommands;
        }

        return null;
    }

    @Override
    public List<EventCommand> findByFriends(User user, Coordinate coordinate) {
        LOG.warn("Getting events...");
        List<Friend> friends = user.getFriends();
        List<User> userFriends = new ArrayList<>();
        for (Friend friend : friends) {
            userFriends.add(userRepository.findByUsername(friend.getUsername()));
        }

        List<Event> events = new ArrayList<>();
        List<EventCommand> eventCommands = new ArrayList<>();
        Iterable<Event> allEvents = eventRepository.findAll();
        Observable.fromIterable(allEvents)
                .filter(event -> distFrom(coordinate.getLatitude(), coordinate.getLongitude(), event.getLatitude(), event.getLongitude()) < 10000)
                .doOnNext(events::add)
                .subscribe(event -> {
                }, throwable -> {
                }, () -> {
                });

        List<Event> collect = events.stream()
                .map(event -> {
                    int friendCount = 0;
                    for (User user1 : event.getUsers()) {

                        for (User userFriend : userFriends) {
                            if (user1.getUserId().equals(userFriend.getUserId())) {
                                friendCount++;
                            }
                        }
                    }
                    event.setFriendCount(friendCount);
                    return event;
                })
                .sorted((o1, o2) -> Integer.compare(o2.getFriendCount(), o1.getFriendCount()))
                .collect(Collectors.toList());

        collect.forEach(event -> eventCommands.add(eventToEventCommand.convert(event)));

        return eventCommands;
    }


    public float distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);

        return dist;
    }
}
