package com.creditsuisse.assignment.event.service;

import com.creditsuisse.assignment.event.model.Event;
import com.creditsuisse.assignment.event.repository.EventRepository;
import com.creditsuisse.assignment.event.service.impl.EventServiceImpl;
import com.creditsuisse.assignment.log.model.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {
    @Mock
    private EventRepository repository;
    @InjectMocks
    private EventService eventService = Mockito.spy( new EventServiceImpl() );

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveEvent() {
        Event entry = new Event(new Log("id", "state", 11L), 4L);
        eventService.addEvent(entry);

        ArgumentCaptor<Event> pathCaptor = ArgumentCaptor.forClass(Event.class);
        verify(repository, times(1)).save(pathCaptor.capture());
        Event capturedEvent = pathCaptor.getValue();
        assertSame(entry, capturedEvent);
    }
}
