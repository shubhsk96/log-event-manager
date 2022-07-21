package com.creditsuisse.assignment.log.service.impl;

import com.creditsuisse.assignment.event.service.EventConverter;
import com.creditsuisse.assignment.log.model.Log;
import com.creditsuisse.assignment.log.service.LogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Implementing class for LogService
 * @author Shubham K
 */
@Log4j2
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventConverter eventConverter;

    /**
     * Read the content of the logs file and parse it
     * @param path Log file path
     */
    @Override
    public void parseLogs(String path) {
        try {
            Files.lines(Paths.get(path)).parallel()
                    .filter(StringUtils::hasText)
                    .forEach(line -> {
                        Log logEntry = parseLogLine(line);
                        eventConverter.convert(logEntry);
                    });
        } catch (IOException e) {
            log.error("Exception occurred during read, path: {}", path);
        }
    }

    /**
     * Helper method to parse a single log record
     * @param logLine single log entry/record
     * @return logEntry
     */
    private Log parseLogLine(String logLine) {
        try {
            Log logEntry = objectMapper.readValue(logLine, Log.class);
            log.info("Line {} is transformed to {}", logLine, logEntry);
            return logEntry;
        } catch (IOException e) {
            log.error("Exception occurred in parsing line: {}", logLine);
            return null;
        }
    }
}
