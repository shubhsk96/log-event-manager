package com.creditsuisse.assignment.log.service;

/**
 * Interface for logs related service
 * @author Shubham K
 */
public interface LogService {
    /**
     * Read the content of the logs file and parse it
     * @param path Log file path
     */
    void parseLogs(String path);
}
