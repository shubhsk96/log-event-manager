package com.creditsuisse.assignment.event.service.impl;

import com.creditsuisse.assignment.event.model.Event;
import com.creditsuisse.assignment.event.repository.EventRepository;
import com.creditsuisse.assignment.event.service.EventService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public void addEvent(Event event) {
        eventRepository.save(event);
    }
}
