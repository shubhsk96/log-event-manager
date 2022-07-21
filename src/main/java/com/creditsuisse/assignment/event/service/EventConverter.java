package com.creditsuisse.assignment.event.service;

import com.creditsuisse.assignment.event.model.Event;
import com.creditsuisse.assignment.log.model.Log;
import org.springframework.core.convert.converter.Converter;

/**
 * Interface for log to event converter service
 * @author Shubham K
 */
public interface EventConverter extends Converter<Log, Event> {
    /**
     * Converts log into event
     * @param logRecord single log entry
     * @return event
     */
    Event convert(Log logRecord);
}
