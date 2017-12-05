package com.mypieceofcode.evfinder.service.reposervices;

import com.mypieceofcode.evfinder.command.Coordinate;
import com.mypieceofcode.evfinder.command.event.EventCommand;
import com.mypieceofcode.evfinder.converters.network.EventCommandToEvent;
import com.mypieceofcode.evfinder.converters.network.EventToEventCommand;
import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.recommender.EventRecommendation;
import com.mypieceofcode.evfinder.repository.EventRepository;
import com.mypieceofcode.evfinder.service.EventService;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            eventCommands.add(eventToEventCommand.convert(event));
        }

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
