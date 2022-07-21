package com.creditsuisse.assignment.event.model;

import com.creditsuisse.assignment.log.model.ApplicationLog;
import com.creditsuisse.assignment.log.model.Log;
import com.creditsuisse.assignment.log.model.Type;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EventTest {
    @Test
    public void createEvent() {
        Event entry = new Event(new Log("id", "state", 1L), 3);
        assertEquals("id", entry.getId());
        assertEquals(3, entry.getDuration());
        assertNull(entry.getType());
        assertNull(entry.getHost());
        assertFalse(entry.isAlert());

        entry = new Event(new Log("id", "state", 1L), 5);
        assertEquals("id", entry.getId());
        assertEquals(5, entry.getDuration());
        assertNull(entry.getType());
        assertNull(entry.getHost());
        assertTrue(entry.isAlert());

        entry = new Event(new ApplicationLog("id", "state", 1L, Type.APPLICATION_LOG.name(), "765"), 3);
        assertEquals("id", entry.getId());
        assertEquals(3, entry.getDuration());
        assertEquals(Type.APPLICATION_LOG, entry.getType());
        assertEquals("765", entry.getHost());
        assertFalse(entry.isAlert());

        entry = new Event(new ApplicationLog("id", "state", 1L, Type.APPLICATION_LOG.name(), "765"), 5);
        assertEquals("id", entry.getId());
        assertEquals(5, entry.getDuration());
        assertEquals(Type.APPLICATION_LOG, entry.getType());
        assertEquals("765", entry.getHost());
        assertTrue(entry.isAlert());
    }
}
