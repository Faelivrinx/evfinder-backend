package com.mypieceofcode.evfinder.repository;

import com.mypieceofcode.evfinder.domain.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

}
