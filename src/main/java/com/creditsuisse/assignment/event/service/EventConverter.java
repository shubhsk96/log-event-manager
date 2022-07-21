package com.creditsuisse.assignment.event.service;

import com.creditsuisse.assignment.event.model.Event;
import com.creditsuisse.assignment.log.model.Log;
import org.springframework.core.convert.converter.Converter;

public interface EventConverter extends Converter<Log, Event> {
    Event convert(Log logRecord);
}
