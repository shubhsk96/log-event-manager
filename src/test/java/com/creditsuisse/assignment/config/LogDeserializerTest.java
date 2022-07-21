package com.creditsuisse.assignment.config;

import com.creditsuisse.assignment.log.model.ApplicationLog;
import com.creditsuisse.assignment.log.model.Log;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogDeserializerTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private JsonParser jsonParser;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private JsonNode jsonNode;

    private LogDeserializer deserializer;

    @Before
    public void setUp() throws IOException {
        deserializer = new LogDeserializer(Log.class);

        when(jsonParser.getCodec().readTree(jsonParser)).thenReturn(jsonNode);

        when(jsonNode.get("id").asText()).thenReturn("id");
        when(jsonNode.get("state").asText()).thenReturn("state");
        when(jsonNode.get("timestamp").asLong()).thenReturn(11L);
    }

    @Test
    public void deserializeLog() throws IOException {
        when(jsonNode.has("type")).thenReturn(false);

        Log logEntry = deserializer.deserialize(jsonParser, null);

        assertFalse(logEntry instanceof ApplicationLog);
        assertEquals("id", logEntry.getId());
        assertEquals("state", logEntry.getState());
        assertEquals(11L, logEntry.getTimestamp());
    }

    @Test
    public void deserializeApplicationLog() throws IOException {
        when(jsonNode.has("type")).thenReturn(true);
        when(jsonNode.get("type").asText()).thenReturn("type");
        when(jsonNode.get("host").asText()).thenReturn("host");

        Log logEntry = deserializer.deserialize(jsonParser, null);

        assertTrue(logEntry instanceof ApplicationLog);
        ApplicationLog applicationLog = (ApplicationLog) logEntry;
        assertEquals("id", applicationLog.getId());
        assertEquals("state", applicationLog.getState());
        assertEquals(11L, applicationLog.getTimestamp());
        assertEquals("type", applicationLog.getType());
        assertEquals("host", applicationLog.getHost());
    }
}
