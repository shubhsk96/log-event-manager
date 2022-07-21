package com.creditsuisse.assignment.event.repository;

import com.creditsuisse.assignment.event.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, String> {
}
