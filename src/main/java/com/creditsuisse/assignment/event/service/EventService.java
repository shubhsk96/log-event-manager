package com.creditsuisse.assignment.event.service;

import com.creditsuisse.assignment.event.model.Event;

/**
 * Interface for events related service
 * @author Shubham K
 */
public interface EventService {
    /**
     * Create an event in database with the required details
     * @param event single event
     */
    void addEvent(Event event);
}
