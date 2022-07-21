package com.creditsuisse.assignment.log;

import com.creditsuisse.assignment.event.service.EventConverter;
import com.creditsuisse.assignment.log.model.Log;
import com.creditsuisse.assignment.log.service.impl.LogServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class LogServiceTest {
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private EventConverter eventConverter;
    @InjectMocks
    private LogServiceImpl logService = Mockito.spy( new LogServiceImpl() );

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks( this );
    }

    @Test
    public void parseLogsSuccessfully() throws IOException {
        String path = ClassLoader.getSystemResource("logfile.txt").getPath();
        logService.parseLogs(path);

        ArgumentCaptor<Log> eventCaptor = ArgumentCaptor.forClass(Log.class);
        verify(eventConverter, times(6)).convert(eventCaptor.capture());
    }
}
