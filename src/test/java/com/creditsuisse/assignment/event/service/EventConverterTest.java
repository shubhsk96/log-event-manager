package com.creditsuisse.assignment.event.service;

import com.creditsuisse.assignment.event.model.Event;
import com.creditsuisse.assignment.event.service.impl.EventConverterImpl;
import com.creditsuisse.assignment.log.model.ApplicationLog;
import com.creditsuisse.assignment.log.model.Log;
import com.creditsuisse.assignment.log.model.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class EventConverterTest {
    @Mock
    private EventService eventService;

    private EventConverterImpl eventConverter;

    private ConcurrentHashMap<String, Log> logsMap;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        logsMap = new ConcurrentHashMap<>();
        eventConverter = new EventConverterImpl(logsMap, eventService);
    }

    @Test
    public void convertFirstLog() {
        Log entry = new Log("id", "state", 1L);
        Event event = eventConverter.convert(entry);

        assertNull(event);
        assertSame(entry, logsMap.get("id"));
    }

    @Test
    public void convertFirstApplicationLog() {
        Log entry = new ApplicationLog("id", "state", 1L, "type", "host");
        Event eventEntry = eventConverter.convert(entry);

        assertNull(eventEntry);
        assertSame(entry, logsMap.get("id"));
    }

    @Test
    public void convertSecondLogWithSameId() {
        Log entry1 = new ApplicationLog("id", "state", 14L, Type.APPLICATION_LOG.name(), "host");
        Log entry2 = new ApplicationLog("id", "state", 17L, Type.APPLICATION_LOG.name(), "host");
        Event eventEntry1 = eventConverter.convert(entry1);
        Event eventEntry2 = eventConverter.convert(entry2);

        assertNull(eventEntry1);
        assertNotNull(eventEntry2);
        assertEquals("id", eventEntry2.getId());
        assertEquals("host", eventEntry2.getHost());
        assertEquals(3L, eventEntry2.getDuration());
        assertEquals(Type.APPLICATION_LOG, eventEntry2.getType());

        assertSame(entry1, logsMap.get("id"));
    }

    @Test
    public void convertRandomLogEntries() {
        Set<Log> logEntries = new HashSet<>();

        for (int i = 0; i < 50; i++) {
            int id = i % 25;
            Log entry = new Log("lid" + id, i < 25 ? "STARTED" : "FINISHED", i % 4);
            logEntries.add(entry);
        }

        for (int i = 50; i < 100; i++) {
            int id = (i - 50) % 25;
            Log entry = new ApplicationLog("alid" + id, i < 75 ? "STARTED" : "FINISHED", i, Type.APPLICATION_LOG.name(), "host" + id);
            logEntries.add(entry);
        }

        Set<Event> eventEntries = new HashSet<>();
        for (Log logEntry : logEntries) {
            Event convert = eventConverter.convert(logEntry);
            if (convert == null) {
                continue;
            }
            eventEntries.add(convert);
        }

        assertEquals(50, eventEntries.size());
        assertEquals(25, eventEntries.stream().filter(eventEntry -> eventEntry.getDuration() > 4).collect(Collectors.toList()).size());
        assertEquals(25, eventEntries.stream().filter(Event::isAlert).collect(Collectors.toList()).size());
        assertTrue(eventEntries.stream().filter(eventEntry -> eventEntry.getDuration() > 4).allMatch(Event::isAlert));
        assertTrue(eventEntries.stream().filter(eventEntry -> StringUtils.hasText(eventEntry.getHost())).allMatch(eventEntry -> eventEntry.getType() == Type.APPLICATION_LOG));
        assertTrue(eventEntries.stream().filter(eventEntry -> eventEntry.getHost() == null).allMatch(eventEntry -> eventEntry.getType() == null));
    }
}
