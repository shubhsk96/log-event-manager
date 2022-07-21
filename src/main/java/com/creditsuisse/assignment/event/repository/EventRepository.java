package com.creditsuisse.assignment.event.repository;

import com.creditsuisse.assignment.event.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to perform database operations
 * @author Shubham K
 */
@Repository
public interface EventRepository extends CrudRepository<Event, String> {
}
